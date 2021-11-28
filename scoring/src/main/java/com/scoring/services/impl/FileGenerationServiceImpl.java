package com.scoring.services.impl;

import com.scoring.dto.*;
import com.scoring.exceptions.FileGenerationException;
import com.scoring.models.ValeurRatio;
import com.scoring.payloads.RapportPayload;
import com.scoring.repository.ValeurRatioRepository;
import com.scoring.services.*;
import com.scoring.utils.Constante;
import com.scoring.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileGenerationServiceImpl implements IFileGenerationService {

    @Autowired
    private IEntrepriseService entrepriseService;

    @Autowired
    private IDirigeantService dirigeantService;

    @Autowired
    private ValeurRatioRepository valeurRatioRepository;

    @Autowired
    private IReferentielService referentielService;

    @Autowired
    private IReportPrinterService reportPrinterService;

    @Value("${app.ged.report.dossier}")
    private String gedRepositoryPath;

    @Value("${app.ged.jasper.dossier}")
    private String gedReportRepositoryPath;

    @Override
    public RapportFile generateRapport(Long id, RapportPayload payload) throws FileGenerationException {
        try {
            EntrepriseDTO entreprise = entrepriseService.getEntreprise(id);
            DirigeantDTO directeur = dirigeantService.getDirigeant(id);
            List<RatioDTO> ratios = referentielService.getlisteRatios();

            Map params = new HashMap<>();

    //        Ratio
            for (RatioDTO ratio : ratios) {
                ValeurRatio valeurRatio = valeurRatioRepository.findByEntreprise_IdAndIdRatio(entreprise.getId(), ratio.getId()).orElse(null);
                if(ratio.getCode().contains(Constante.RATIO_LIQUIDITE) && valeurRatio != null){
                    params.put("uRatioLiquidite", ratio.getUnite());
                    params.put("rRatioLiquidite", valeurRatio.getValeur());
                }
                if(ratio.getCode().contains(Constante.RATIO_RENTABILITE) && valeurRatio != null){
                    params.put("uRatioRentabilite", ratio.getUnite());
                    params.put("rRatioRentabilite", valeurRatio.getValeur());
                }
                if(ratio.getCode().contains(Constante.RATIO_CAPACITE_REMBOURSEMENT) && valeurRatio != null){
                    params.put("uCapacite", ratio.getUnite());
                    params.put("rCapacite", valeurRatio.getValeur());
                }
                if(ratio.getCode().contains(Constante.RATIO_AUTONOMIE_FINANCIERE) && valeurRatio != null){
                    params.put("uAutonomie", ratio.getUnite());
                    params.put("rAutonomie", valeurRatio.getValeur());
                }
                if(ratio.getCode().contains(Constante.RATIO_DELAI_CLIENT) && valeurRatio != null){
                    params.put("uDelaiClient", ratio.getUnite());
                    params.put("rDelaiClient", valeurRatio.getValeur());
                }
                if(ratio.getCode().contains(Constante.RATIO_DELAI_FOURNISSEUR) && valeurRatio != null){
                    params.put("uDelaiFournisseur", ratio.getUnite());
                    params.put("rDelaiFournisseur", valeurRatio.getValeur());
                }
            }
    //            Entreprise infos
            params.put("siege", entreprise.getAdresse());
            params.put("ville", "");
            params.put("telephone", directeur.getMobile());
            params.put("email", directeur.getEmail());
            params.put("dateFormated", DateUtils.formatDate(new Date()));
            params.put("commentaires", payload.getCommentaire());
            params.put("recommendations", payload.getRecommendation());
            params.put("formeJuridique", entreprise.getFormeJur().getLibelle());
            params.put("dirigeant", directeur.getPrenom()+""+directeur.getNom());

            String secteurs = "";
            for (SecteurActiviteDTO secteurActivite : entreprise.getSecteurs()){
                secteurs += secteurActivite.getLibelle();
                secteurs += ", ";
            }
            params.put("secteurActivite", secteurs);

            params.put("activitePrincipale", (entreprise.getSecteurs()).get(0).getLibelle());
            params.put("capitalSocial", entreprise.getCapital());
            params.put("dateDemarrage", entreprise.getAnnee());
            params.put("raisonSociale", entreprise.getRaisonSociale());
            params.put("libelleSigle", "");
            params.put("activite", "");
            params.put("idu", "");

            String filename = "Rapport "+entreprise.getIntitule();

            Path gedPath = Paths.get(gedReportRepositoryPath).toAbsolutePath();
            String jasperFile  = gedPath.resolve("rapport_scoreCI.jrxml").toString();

            filename = reportPrinterService.print(jasperFile, filename, IReportPrinterService.TypeFile.PDF, params);

            Path path = Paths.get(gedRepositoryPath).toAbsolutePath().resolve(filename);

            File file = new File(path.toUri());
            InputStream is = new FileInputStream(file);

            RapportFile rapport = new RapportFile();
            rapport.setName(filename);
            rapport.setContent(is.readAllBytes());
        }
        catch (Exception e){
            throw new FileGenerationException(e.getMessage(), e);
        }
        return null;
    }
}
