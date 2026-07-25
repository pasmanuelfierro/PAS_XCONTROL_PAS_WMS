package com.pas.xcontrolwms.xcontrolmapping.entradas;

import com.pas.xcontrolwms.xcontrolmapping.XControlProductoDTO;
import lombok.Data;

import java.util.List;

@Data
public class XControlEntradaDTO {

    private Long id;
    private String fecha;
    private Long embarqueId;
    private String observaciones;
    private List<String> facturas;
    private List<XControlProductoDTO> productos;
}
