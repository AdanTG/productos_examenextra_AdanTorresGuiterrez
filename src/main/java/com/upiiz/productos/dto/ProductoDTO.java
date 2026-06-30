package com.upiiz.productos.dto;

import com.upiiz.productos.model.Producto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer existencia;
    private String categoria;
    private LocalDate fechaRegistro;

    public ProductoDTO(Producto p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.descripcion = p.getDescripcion();
        this.precio = p.getPrecio();
        this.existencia = p.getExistencia();
        this.categoria = p.getCategoria();
        this.fechaRegistro = p.getFechaRegistro();
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public Integer getExistencia() { return existencia; }
    public String getCategoria() { return categoria; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
}
