package com.pas.xcontrolwms.wms.model;

import com.pas.xcontrolwms.wms.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "explosive_registry", schema = "explosive")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)

public class ExplosiveRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo",nullable = false, length = 20)
    private TipoMovimiento tipo;

    @Column(name = "fecha", updatable = false)
    private OffsetDateTime fecha;

    // Entrada
    @Column(name = "embarque_id", unique = true)
    private Long embarqueId;

    // Id del registro de XControl
    @Column(name = "xcontrol_registry_id", unique = true)
    private Long xcontrolRegistryId;

    @Column(name = "compania")
    private String compania;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "repartidor")
    private String repartidor;

    @Column(name = "cargador")
    private String cargador;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "turno")
    private Integer turno;

    @Column(name = "tipo_vale")
    private Integer tipoVale;

    @ElementCollection
    @CollectionTable(
            name = "movimiento_factura",
            schema = "wms",
            joinColumns = @JoinColumn(name = "movimiento_id")
    )
    @Column(name = "factura")
    private List<String> facturas;

    @OneToMany(
            mappedBy = "explosiveRegistry",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExplosiveProduct> products = new ArrayList<>();

    public void addProduct(ExplosiveProduct product) {
        products.add(product);
        product.setExplosiveRegistry(this);
    }

    public void removeProduct(ExplosiveProduct product) {
        products.remove(product);
        product.setExplosiveRegistry(null);
    }


}