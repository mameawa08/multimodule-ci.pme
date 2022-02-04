package com.scoring.controllers;

import com.scoring.dto.DemandeAccompagnementDTO;
import com.scoring.services.IDemandeAccompagnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/demandes-accompagnements")
public class DemandeAccompagnementController {

    @Autowired
    private IDemandeAccompagnementService demandeAccompagnementService;

    @PostMapping(path = "/{idDemandeScoring}")
    private ResponseEntity createDemandeAccompagnement(@PathVariable Long idDemandeScoring){
        try {
            DemandeAccompagnementDTO demande = demandeAccompagnementService.createDemandeAccompagnement(idDemandeScoring);
            return ResponseEntity.ok(demande);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/{idDemandeAccompagnement}/envoyer", method = {RequestMethod.PATCH, RequestMethod.PUT})
    private ResponseEntity envoyerDemandeAccompagnement(@PathVariable Long idDemandeAccompagnement){
        try {
            DemandeAccompagnementDTO demande = demandeAccompagnementService.envoyerDemandeAccompagnement(idDemandeAccompagnement);
            return ResponseEntity.ok(demande);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/{idDemandeAccompagnement}/receptionner", method = {RequestMethod.PATCH, RequestMethod.PUT})
    private ResponseEntity receptionnerDemandeAccompagnement(@PathVariable Long idDemandeAccompagnement){
        try {
            DemandeAccompagnementDTO demande = demandeAccompagnementService.receptionnerDemandeAccompagnement(idDemandeAccompagnement);
            return ResponseEntity.ok(demande);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{idDemandeAccompagnement}")
    private ResponseEntity annulerDemandeAccompagnement(@PathVariable Long idDemandeAccompagnement){
        try {
            boolean rs = demandeAccompagnementService.annulerDemandeAccompagnement(idDemandeAccompagnement);
            return ResponseEntity.ok(rs);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
