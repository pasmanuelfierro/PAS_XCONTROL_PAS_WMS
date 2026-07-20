package com.pas.xcontrolwms.dto.xcontrol;

import lombok.Data;

import java.util.List;

@Data
public class XControlProductoDTO {
    private Long id;
    private String nombre;
    private Integer segmento;
    private Integer cantidad;

    private List<XControlCajaDTO> cajas;
    private List<String> folios;
}
