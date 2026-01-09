package com.proiect.service.controller;

/** Clasa Controller pentru Pagina de Statistici (Cerinte Interogari)
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.repository.StatisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticaController {

    @Autowired
    private StatisticaRepository statisticaRepository;

    @GetMapping("/statistici")
    public String arataStatistici(Model model) {
        // Incarcam datele pentru interogarile complexe
        model.addAttribute("clientiDefecti", statisticaRepository.clientiCuDispozitiveDefecte());
        model.addAttribute("angajatiTop", statisticaRepository.angajatiTopManopera());
        model.addAttribute("pieseCritice", statisticaRepository.pieseStocCritic());
        model.addAttribute("dispozitivScump", statisticaRepository.dispozitivScump());

        // Incarcam datele pentru interogarile simple (JOIN)
        model.addAttribute("venituriAngajati", statisticaRepository.venituriPerAngajat());
        model.addAttribute("interventiiBrand", statisticaRepository.interventiiPerBrand());

        return "statistici";
    }
}