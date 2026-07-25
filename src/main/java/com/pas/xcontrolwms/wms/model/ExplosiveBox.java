package com.pas.xcontrolwms.wms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "explosive_box", schema = "explosive")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ExplosiveBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "explosive_product_id")
    private ExplosiveProduct explosiveProduct;

    @Column(name = "codigo")
    private String codigo;

    @OneToMany(
            mappedBy = "box",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExplosiveBoxFolio> folios = new ArrayList<>();

    public void addFolio(ExplosiveBoxFolio folio) {
        folios.add(folio);
        folio.setBox(this);
    }

    public void removeFolio(ExplosiveBoxFolio folio) {
        folios.remove(folio);
        folio.setBox(null);
    }

}