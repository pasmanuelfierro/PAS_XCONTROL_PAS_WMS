package com.pas.xcontrolwms.wms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "explosive_box_folio", schema = "explosive")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ExplosiveBoxFolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "box_id", nullable = false)
    private ExplosiveBox box;

    @Column(name = "folio")
    private String folio;

}