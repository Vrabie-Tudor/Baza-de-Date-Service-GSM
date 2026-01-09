package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea operațiilor legate de Clienți
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.validation.Valid;
import com.proiect.service.entity.Client;
import com.proiect.service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clienti")
    public String arataClienti(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Client> lista;

        if (keyword != null && !keyword.isEmpty()) {
            lista = clientRepository.cautaClient(keyword);
        } else {
            lista = clientRepository.toataListaClienti();
        }

        model.addAttribute("listaClienti", lista);
        model.addAttribute("keyword", keyword);
        model.addAttribute("clientNou", new Client());
        return "clienti";
    }

    @PostMapping("/clienti/adauga")
    public String adaugaClient(
            @Valid @ModelAttribute("clientNou") Client client,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("listaClienti", clientRepository.toataListaClienti());
            return "clienti";
        }
        clientRepository.adaugaClient(client.getNume(), client.getPrenume(), client.getTelefon(), client.getEmail(), client.getAdresa());

        return "redirect:/clienti";
    }

    @GetMapping("/clienti/editeaza/{id}")
    public String arataFormularEditare(@PathVariable("id") Integer id, Model model) {
        Client client = clientRepository.gasesteDupaId(id);
        model.addAttribute("clientDeEditat", client);
        return "editeaza_client";
    }

    @PostMapping("/clienti/modifica")
    public String salveazaModificarea(
            @RequestParam Integer clientID,
            @RequestParam String nume,
            @RequestParam String prenume,
            @RequestParam String telefon,
            @RequestParam String email,
            @RequestParam String adresa) {

        clientRepository.modificaClient(nume, prenume, telefon, email, adresa, clientID);
        return "redirect:/clienti";
    }

    @GetMapping("/clienti/sterge/{id}")
    public String stergeClient(@PathVariable("id") Integer id) {
        clientRepository.stergeClient(id);
        return "redirect:/clienti";
    }
}