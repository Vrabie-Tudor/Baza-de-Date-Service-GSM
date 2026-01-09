package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea Facturilor
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Factura;
import com.proiect.service.entity.Interventie;
import com.proiect.service.entity.InterventiePiesa;
import com.proiect.service.repository.FacturaRepository;
import com.proiect.service.repository.InterventiePiesaRepository;
import com.proiect.service.repository.InterventieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private InterventieRepository interventieRepository;
    @Autowired
    private InterventiePiesaRepository interventiePiesaRepository;

    @GetMapping("/facturi")
    public String arataFacturi(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Factura> lista;
        if (keyword != null && !keyword.isEmpty()) {
            lista = facturaRepository.cautaFactura(keyword);
        } else {
            lista = facturaRepository.toataListaFacturi();
        }

        List<Interventie> toateInterventiile = interventieRepository.toataListaInterventii();

        model.addAttribute("listaFacturi", lista);
        model.addAttribute("listaInterventii", toateInterventiile);
        model.addAttribute("keyword", keyword);
        return "facturi";
    }

    @PostMapping("/facturi/genereaza")
    public String genereazaFactura(@RequestParam Integer interventieID) {

        Interventie interventie = interventieRepository.gasesteDupaId(interventieID);

        Double costManopera = interventie.getCostManopera();
        if(costManopera == null) costManopera = 0.0;

        Double costPiese = 0.0;
        List<InterventiePiesa> pieseFolosite = interventiePiesaRepository.toataLista();
        for(InterventiePiesa ip : pieseFolosite) {
            if(ip.getId().getInterventieID().equals(interventieID)) {
                costPiese += ip.getCostTotal();
            }
        }

        Double total = costManopera + costPiese;

        Factura factura = new Factura();
        factura.setInterventie(interventie);
        factura.setClient(interventie.getDispozitiv().getClient());
        factura.setDataEmitere(LocalDate.now());
        factura.setSumaTotala(total);
        factura.setProfit(total);

        facturaRepository.save(factura);


        interventie.setStatus("Finalizata");
        interventie.setDataFinalizare(LocalDate.now());

        interventieRepository.save(interventie);

        return "redirect:/facturi";
    }

    @GetMapping("/facturi/sterge/{id}")
    public String stergeFactura(@PathVariable("id") Integer id) {
        facturaRepository.deleteById(id);
        return "redirect:/facturi";
    }
}