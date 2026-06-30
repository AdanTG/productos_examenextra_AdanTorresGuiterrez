package com.upiiz.productos.controller;

import com.upiiz.productos.dto.ProductoDTO;
import com.upiiz.productos.model.Producto;
import com.upiiz.productos.model.Usuario;
import com.upiiz.productos.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        model.addAttribute("productos", productoService.listarTodos());
        agregarDatosUsuario(model, session);
        return "productos/listar";
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResponseEntity<?> guardar(@Valid @RequestBody Producto producto, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            for (FieldError fe : result.getFieldErrors()) {
                errores.put(fe.getField(), fe.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errores);
        }

        if (producto.getId() != null) {
            Producto existente = productoService.obtenerPorId(producto.getId());
            if (existente != null && producto.getFechaRegistro() == null) {
                producto.setFechaRegistro(existente.getFechaRegistro());
            }
        }

        Producto guardado = productoService.guardar(producto);
        return ResponseEntity.ok(new ProductoDTO(guardado));
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<ProductoDTO> detalleJson(@PathVariable Integer id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProductoDTO(producto));
    }

    private void agregarDatosUsuario(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
    }
}
