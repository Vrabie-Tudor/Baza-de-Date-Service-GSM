package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea asocierii dintre Intervenții și Piese
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Piesa;
import com.proiect.service.repository.InterventiePiesaRepository;
import com.proiect.service.repository.InterventieRepository;
import com.proiect.service.repository.PiesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InterventiePiesaController {

    @Autowired
    private InterventiePiesaRepository repo;
    @Autowired
    private InterventieRepository interventieRepo;
    @Autowired
    private PiesaRepository piesaRepo;

    @GetMapping("/interventii-piese")
    public String arataPagina(Model model) {
        model.addAttribute("listaAsocieri", repo.toataLista());
        model.addAttribute("listaInterventii", interventieRepo.toataListaInterventii());
        model.addAttribute("listaPiese", piesaRepo.toataListaPiese());
        return "interventii_piese";
    }

    @PostMapping("/interventii-piese/adauga")
    public String adaugaAsociere(
            @RequestParam Integer interventieID,
            @RequestParam Integer piesaID,
            @RequestParam Integer cantitate) {

        Piesa piesa = piesaRepo.gasesteDupaId(piesaID);
        Double costTotal = piesa.getPret() * cantitate;

        repo.adauga(interventieID, piesaID, cantitate, costTotal);

        return "redirect:/interventii-piese";
    }

    @GetMapping("/interventii-piese/sterge")
    public String stergeAsociere(
            @RequestParam Integer interventieID,
            @RequestParam Integer piesaID) {

        repo.sterge(interventieID, piesaID);
        return "redirect:/interventii-piese";
    }
}