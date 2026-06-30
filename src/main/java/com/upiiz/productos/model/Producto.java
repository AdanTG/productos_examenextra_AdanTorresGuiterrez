package com.upiiz.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "Precio invalido")
    @Column(name = "precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "La existencia es obligatoria")
    @Min(value = 0, message = "La existencia no puede ser negativa")
    @Column(name = "existencia", nullable = false)
    private Integer existencia;

    @NotBlank(message = "La categoria es obligatoria")
    @Size(max = 100, message = "La categoria no puede exceder 100 caracteres")
    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @PrePersist
    public void prePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDate.now();
        }
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Integer getExistencia() { return existencia; }
    public void setExistencia(Integer existencia) { this.existencia = existencia; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
