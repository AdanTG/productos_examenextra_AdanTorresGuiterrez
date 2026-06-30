package com.upiiz.productos.service;

import com.upiiz.productos.model.Usuario;
import com.upiiz.productos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario autenticar(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
