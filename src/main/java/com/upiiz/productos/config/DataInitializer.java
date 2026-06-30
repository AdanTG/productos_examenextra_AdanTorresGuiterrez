package com.upiiz.productos.config;

import com.upiiz.productos.model.Producto;
import com.upiiz.productos.model.Rol;
import com.upiiz.productos.model.Usuario;
import com.upiiz.productos.repository.ProductoRepository;
import com.upiiz.productos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {

        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("Administrador del Sistema", "admin", "admin123", Rol.ADMINISTRADOR));
            usuarioRepository.save(new Usuario("Usuario Capturista", "capturista", "captura123", Rol.CAPTURISTA));
        }

        if (productoRepository.count() == 0) {
            productoRepository.save(crear("Laptop Lenovo ThinkPad", "Laptop Core i7, 16GB RAM, 512GB SSD",
                    new BigDecimal("18500.00"), 10, "Computadoras"));
            productoRepository.save(crear("Monitor Dell 24\"", "Monitor Full HD IPS 75Hz",
                    new BigDecimal("3200.50"), 25, "Monitores"));
            productoRepository.save(crear("Teclado Mecanico Redragon", "Switches rojos, retroiluminacion RGB",
                    new BigDecimal("899.00"), 40, "Perifericos"));
            productoRepository.save(crear("Mouse Logitech MX Master 3", "Mouse inalambrico ergonomico",
                    new BigDecimal("1750.00"), 15, "Perifericos"));
            productoRepository.save(crear("SSD Kingston 1TB", "Unidad de estado solido NVMe M.2",
                    new BigDecimal("1450.99"), 30, "Almacenamiento"));
        }
    }

    private Producto crear(String nombre, String descripcion, BigDecimal precio, int existencia, String categoria) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setExistencia(existencia);
        p.setCategoria(categoria);
        p.setFechaRegistro(LocalDate.now());
        return p;
    }
}
