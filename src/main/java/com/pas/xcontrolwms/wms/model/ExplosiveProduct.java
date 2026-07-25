package com.pas.xcontrolwms.wms.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "explosive_product", schema = "explosive")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ExplosiveProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "explosive_registry_id" )
    private ExplosiveRegistry explosiveRegistry;

    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "segmento")
    private Integer segmento;

    @Column(name = "cantidad")
    private Integer cantidad;

    //SOLO SE USA EN ENTRADA
    @Column(name = "peso", precision = 10, scale = 2)
    private BigDecimal peso;

    @OneToMany(
            mappedBy = "explosiveProduct",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExplosiveBox> boxes = new ArrayList<>();

/*    @OneToMany(
            mappedBy = "explosiveProduct",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExplosiveBoxFolio> folios = new ArrayList<>();*/

}