package com.proiect.service.controller;

/** Clasa Controller pentru gestionarea autentificării utilizatorilor
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Utilizator;
import com.proiect.service.repository.UtilizatorRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String proceseazaLogin(@RequestParam String username,
                                  @RequestParam String parola,
                                  Model model,
                                  HttpSession session) {

        Utilizator userGasit = utilizatorRepository.verificaCredidentiale(username, parola);

        if (userGasit != null) {
            session.setAttribute("userLogat", userGasit.getUsername());

            return "redirect:/clienti";
        } else {
            model.addAttribute("eroare", "Username sau parolă incorectă!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}