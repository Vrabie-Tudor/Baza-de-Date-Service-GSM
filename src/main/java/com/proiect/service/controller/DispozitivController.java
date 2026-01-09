package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea operațiilor legate de Dispozitive
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Dispozitiv;
import com.proiect.service.repository.ClientRepository;
import com.proiect.service.repository.DispozitivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DispozitivController {

    @Autowired
    private DispozitivRepository dispozitivRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/dispozitive")
    public String arataDispozitive(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Dispozitiv> lista;

        if (keyword != null && !keyword.isEmpty()) {
            lista = dispozitivRepository.cautaDispozitiv(keyword);
        } else {
            lista = dispozitivRepository.toataListaDispozitive();
        }

        model.addAttribute("listaDispozitive", lista);
        model.addAttribute("listaClienti", clientRepository.toataListaClienti());
        model.addAttribute("keyword", keyword);
        return "dispozitive";
    }

    @PostMapping("/dispozitive/adauga")
    public String adaugaDispozitiv(
            @RequestParam String tipDispozitiv,
            @RequestParam String producator,
            @RequestParam String model,
            @RequestParam String serie,
            @RequestParam String stare,
            @RequestParam Integer clientID) {

        dispozitivRepository.adaugaDispozitiv(tipDispozitiv, producator, model, serie, stare, clientID);
        return "redirect:/dispozitive";
    }

    @GetMapping("/dispozitive/sterge/{id}")
    public String stergeDispozitiv(@PathVariable("id") Integer id) {
        try {
            dispozitivRepository.stergeDispozitiv(id);
        } catch (Exception e) {
            System.out.println("NU SE POATE STERGE: Dispozitivul are istoric de interventii.");
        }
        return "redirect:/dispozitive";
    }

    @GetMapping("/dispozitive/editeaza/{id}")
    public String arataFormularEditare(@PathVariable("id") Integer id, Model model) {
        Dispozitiv dispozitiv = dispozitivRepository.gasesteDupaId(id);
        if (dispozitiv == null) return "redirect:/dispozitive";

        model.addAttribute("dispozitivDeEditat", dispozitiv);
        model.addAttribute("listaClienti", clientRepository.toataListaClienti());
        return "editeaza_dispozitiv";
    }

    @PostMapping("/dispozitive/modifica")
    public String salveazaModificarea(
            @RequestParam Integer dispozitivID,
            @RequestParam String tipDispozitiv,
            @RequestParam String producator,
            @RequestParam String model,
            @RequestParam String serie,
            @RequestParam String stare,
            @RequestParam Integer clientID) {

        dispozitivRepository.modificaDispozitiv(tipDispozitiv, producator, model, serie, stare, clientID, dispozitivID);
        return "redirect:/dispozitive";
    }
}