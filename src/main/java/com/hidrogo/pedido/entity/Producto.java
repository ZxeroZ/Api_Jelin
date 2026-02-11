package com.hidrogo.pedido.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String codigo;
    @Column(name = "precio unitario", precision = 0, scale = 2,nullable = false)
    private BigDecimal precioUnitario;
}


