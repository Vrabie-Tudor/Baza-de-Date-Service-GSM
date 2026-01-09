package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea stocului de Piese
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Piesa;
import com.proiect.service.repository.PiesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PiesaController {

    @Autowired
    private PiesaRepository piesaRepository;

    @GetMapping("/piese")
    public String arataPiese(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Piesa> lista;

        if (keyword != null && !keyword.isEmpty()) {
            lista = piesaRepository.cautaPiesa(keyword);
        } else {
            lista = piesaRepository.toataListaPiese();
        }

        model.addAttribute("listaPiese", lista);
        model.addAttribute("keyword", keyword);
        return "piese";
    }

    @PostMapping("/piese/adauga")
    public String adaugaPiesa(
            @RequestParam String denumire,
            @RequestParam String producator,
            @RequestParam Integer stoc,
            @RequestParam Double pret) {

        piesaRepository.adaugaPiesa(denumire, producator, stoc, pret);
        return "redirect:/piese";
    }

    @GetMapping("/piese/editeaza/{id}")
    public String arataFormularEditare(@PathVariable("id") Integer id, Model model) {
        Piesa piesa = piesaRepository.gasesteDupaId(id);
        if (piesa == null) return "redirect:/piese";

        model.addAttribute("piesaDeEditat", piesa);
        return "editeaza_piesa";
    }

    @PostMapping("/piese/modifica")
    public String modificaPiesa(
            @RequestParam Integer piesaID,
            @RequestParam String denumire,
            @RequestParam String producator,
            @RequestParam Integer stoc,
            @RequestParam Double pret) {

        piesaRepository.modificaPiesa(denumire, producator, stoc, pret, piesaID);
        return "redirect:/piese";
    }

    @GetMapping("/piese/sterge/{id}")
    public String stergePiesa(@PathVariable("id") Integer id) {
        piesaRepository.stergePiesa(id);
        return "redirect:/piese";
    }
}