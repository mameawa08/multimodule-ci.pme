package com.scoring.services.impl;

import java.util.Date;
import java.util.List;

import com.scoring.config.AccessTokenDetails;
import com.scoring.dto.PieceJointeDTO;
import com.scoring.exceptions.PieceJointeException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;
import com.scoring.models.PieceJointe;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.IndicateurRepository;
import com.scoring.repository.PieceJointeRepository;
import com.scoring.services.IPieceJointeService;

import com.scoring.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PieceJointeServiceImpl implements IPieceJointeService {

	@Autowired
	private PieceJointeRepository pieceJointeRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private PayloadToDTO payloadToDTO;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private IndicateurRepository indicateurRepository;

	@Autowired
	private IUserService userService;

	@Autowired
	private EntrepriseRepository entrepriseRepository;


    @Override
	public List<PieceJointeDTO> getPieceJointes(Long id) throws PieceJointeException{
		Indicateur indicateur = indicateurRepository.findById(id).orElseThrow(() -> new PieceJointeException("Piece jointe :: Indicateur "+id+" not found."));
		List<PieceJointe> pieceJointes = pieceJointeRepository.findByIndicateur(indicateur);
		return dtoFactory.createListPieceJointe(pieceJointes);
	}

	@Override
	public PieceJointeDTO getPieceJointe(Long id) throws PieceJointeException{
		PieceJointe pieceJointe = pieceJointeRepository.findById(id).orElseThrow(() -> new PieceJointeException("PieceJointe :: "+id+" not found."));
		return dtoFactory.createPieceJointe(pieceJointe);
	}

	@Override
	public boolean createPieceJointe(Long id, MultipartFile[] files, boolean logo) throws PieceJointeException{
		Indicateur indicateur = null;
        Entreprise entreprise = null;
        if(logo){
            entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new PieceJointeException("Piece jointe :: Entreprise "+id+" not found."));
        }else {
            indicateur = indicateurRepository.findById(id).orElseThrow(() -> new PieceJointeException("Piece jointe :: Indicateur "+id+" not found."));
        }

        if(files != null && files.length > 0){
			for(MultipartFile file : files){
				PieceJointeDTO pieceJointe = new PieceJointeDTO();
				pieceJointe.setDateCreation(new Date());
				pieceJointe.setNomPiece(StringUtils.cleanPath(file.getOriginalFilename()));
				try {
					pieceJointe.setContenu(file.getBytes());

					PieceJointe model = modelFactory.createPieceJointe(pieceJointe);
					if (logo){
					    model.setEntreprise(entreprise);

                    }else{
					    model.setIndicateur(indicateur);
                    }

					pieceJointeRepository.save(model);
					pieceJointe.setId(model.getId());
				} catch (Exception e) {
					throw new PieceJointeException("Piece jointe :: "+e.getMessage(), e);
				}
			}
		}
		return true;
	}

	@Override
	public boolean switchStatus(Long id) throws PieceJointeException{
		PieceJointe pieceJointe = pieceJointeRepository.findById(id).orElseThrow(() -> new PieceJointeException("PieceJointe :: "+id+" not found."));
		try {
			pieceJointe.setActif(pieceJointe.isActif() ? false : true);
			pieceJointeRepository.save(pieceJointe);
			return true;
		} catch (Exception e) {
			throw new PieceJointeException(e.getMessage(), e);
		}
	}


    @Override
    public boolean deleteAttachment(Long id, Long pieceId, boolean logo) throws PieceJointeException {
        AccessTokenDetails user = userService.getConnectedUser();
        Indicateur indicateur = null;
        Entreprise entreprise = null;
        if (logo){
            indicateur = indicateurRepository.findById(id).orElseThrow(() -> new PieceJointeException("Indicateur "+id+" not found."));
        }else{
            entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new PieceJointeException("Entreprise "+id+" not found."));
        }

        PieceJointe piece = pieceJointeRepository.findById(pieceId).orElseThrow(() -> new PieceJointeException("Not found."));

        if((logo && piece.getUser().equals(user.getUserId()) && indicateur.getId().equals(piece.getIndicateur().getId()))
                ||
                (!logo && piece.getUser().equals(user.getUserId()) && entreprise.getId().equals(piece.getEntreprise().getId()))
        ){
            pieceJointeRepository.delete(piece);
            return true;
        }
        throw new PieceJointeException("Vous ne pouvez pas supprimer cette piece jointe.");
    }

    @Override
    public boolean updateNom(Long id,String nom) throws PieceJointeException {
        PieceJointe piece=pieceJointeRepository.findById(id).orElseThrow(()-> new PieceJointeException("Not found."));
        String extension = getExtentionFichier(piece.getNomPiece());
        String nouveauNom = nom.split("\\.")[0];
        piece.setNomPiece(nouveauNom+"."+extension);
        pieceJointeRepository.save(piece);
        return true;
    }


    private String getExtentionFichier(String fileName) {
        String[] tmp = fileName.split("\\.");
        return tmp[tmp.length - 1];
    }

}
