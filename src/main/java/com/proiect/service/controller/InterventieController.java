package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea Intervențiilor de service
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Interventie;
import com.proiect.service.repository.AngajatRepository;
import com.proiect.service.repository.DispozitivRepository;
import com.proiect.service.repository.InterventiePiesaRepository; // Import nou
import com.proiect.service.repository.InterventieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class InterventieController {

    @Autowired
    private InterventieRepository interventieRepository;
    @Autowired
    private DispozitivRepository dispozitivRepository;
    @Autowired
    private AngajatRepository angajatRepository;
    @Autowired
    private InterventiePiesaRepository interventiePiesaRepository;

    @GetMapping("/interventii")
    public String arataInterventii(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Interventie> lista;

        if (keyword != null && !keyword.isEmpty()) {
            lista = interventieRepository.cautaInterventie(keyword);
        } else {
            lista = interventieRepository.toataListaInterventii();
        }

        model.addAttribute("listaInterventii", lista);
        model.addAttribute("listaDispozitive", dispozitivRepository.toataListaDispozitive());
        model.addAttribute("listaAngajati", angajatRepository.toataListaAngajati());
        model.addAttribute("keyword", keyword);

        return "interventii";
    }

    @PostMapping("/interventii/adauga")
    public String adaugaInterventie(
            @RequestParam String descriereProblema,
            @RequestParam String solutieAplicata,
            @RequestParam LocalDate dataStart,
            @RequestParam(required = false) LocalDate dataFinalizare,
            @RequestParam Double costManopera,
            @RequestParam String status,
            @RequestParam Integer dispozitivID,
            @RequestParam Integer angajatID) {

        interventieRepository.adaugaInterventie(descriereProblema, solutieAplicata, dataStart, dataFinalizare, costManopera, status, dispozitivID, angajatID);
        return "redirect:/interventii";
    }

    @GetMapping("/interventii/editeaza/{id}")
    public String arataFormularEditare(@PathVariable("id") Integer id, Model model) {
        Interventie interventie = interventieRepository.gasesteDupaId(id);
        if (interventie == null) return "redirect:/interventii";

        model.addAttribute("interventieDeEditat", interventie);
        model.addAttribute("listaDispozitive", dispozitivRepository.toataListaDispozitive());
        model.addAttribute("listaAngajati", angajatRepository.toataListaAngajati());
        return "editeaza_interventie";
    }

    @PostMapping("/interventii/modifica")
    public String modificaInterventie(
            @RequestParam Integer interventieID,
            @RequestParam String descriereProblema,
            @RequestParam String solutieAplicata,
            @RequestParam LocalDate dataStart,
            @RequestParam(required = false) LocalDate dataFinalizare,
            @RequestParam Double costManopera,
            @RequestParam String status,
            @RequestParam Integer dispozitivID,
            @RequestParam Integer angajatID) {

        interventieRepository.modificaInterventie(descriereProblema, solutieAplicata, dataStart, dataFinalizare, costManopera, status, dispozitivID, angajatID, interventieID);
        return "redirect:/interventii";
    }

    @GetMapping("/interventii/sterge/{id}")
    public String stergeInterventie(@PathVariable("id") Integer id) {
        try {
            interventiePiesaRepository.stergeToatePieseleInterventiei(id);

            interventieRepository.stergeInterventie(id);
        } catch (Exception e) {
            System.out.println("Eroare la stergere interventie: " + e.getMessage());
        }
        return "redirect:/interventii";
    }
}