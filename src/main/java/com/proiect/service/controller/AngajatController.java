package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea operațiilor legate de Angajați
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Angajat;
import com.proiect.service.repository.AngajatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AngajatController {

    @Autowired
    private AngajatRepository angajatRepository;

    @GetMapping("/angajati")
    public String arataAngajati(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Angajat> lista;

        if (keyword != null && !keyword.isEmpty()) {
            lista = angajatRepository.cautaAngajat(keyword);
        } else {
            lista = angajatRepository.toataListaAngajati();
        }

        model.addAttribute("listaAngajati", lista);
        model.addAttribute("keyword", keyword);
        return "angajati";
    }

    @PostMapping("/angajati/adauga")
    public String adaugaAngajat(
            @RequestParam String nume,
            @RequestParam String prenume,
            @RequestParam String functie,
            @RequestParam String telefon,
            @RequestParam String email,
            @RequestParam LocalDate dataAngajare) {

        angajatRepository.adaugaAngajat(nume, prenume, functie, telefon, email, dataAngajare);
        return "redirect:/angajati";
    }

    @GetMapping("/angajati/sterge/{id}")
    public String stergeAngajat(@PathVariable("id") Integer id) {
        try {
            angajatRepository.stergeAngajat(id);
        } catch (Exception e) {
            System.out.println("NU SE POATE STERGE: Angajatul are interventii asociate.");
        }
        return "redirect:/angajati";
    }

    @GetMapping("/angajati/editeaza/{id}")
    public String arataFormularEditare(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("angajatDeEditat", angajatRepository.gasesteDupaId(id));
        return "editeaza_angajat";
    }

    @PostMapping("/angajati/modifica")
    public String modificaAngajat(
            @RequestParam Integer angajatID,
            @RequestParam String nume,
            @RequestParam String prenume,
            @RequestParam String functie,
            @RequestParam String telefon,
            @RequestParam String email,
            @RequestParam LocalDate dataAngajare) {

        angajatRepository.modificaAngajat(nume, prenume, functie, telefon, email, dataAngajare, angajatID);
        return "redirect:/angajati";
    }
}