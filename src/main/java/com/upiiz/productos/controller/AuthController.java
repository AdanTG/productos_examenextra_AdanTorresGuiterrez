package com.upiiz.productos.controller;

import com.upiiz.productos.model.Usuario;
import com.upiiz.productos.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String raiz(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/productos";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session, Model model) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/productos";
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        Usuario usuario = usuarioService.autenticar(username, password);

        if (usuario == null) {
            model.addAttribute("error", "Usuario o contrasena incorrectos");
            return "login";
        }

        session.setAttribute("usuario", usuario);
        return "redirect:/productos";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
