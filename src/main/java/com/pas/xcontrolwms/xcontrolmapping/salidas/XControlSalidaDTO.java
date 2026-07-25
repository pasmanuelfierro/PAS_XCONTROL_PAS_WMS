package com.pas.xcontrolwms.xcontrolmapping.salidas;

import com.pas.xcontrolwms.xcontrolmapping.XControlProductoDTO;
import lombok.Data;

import java.util.List;

@Data
public class XControlSalidaDTO {

    private Long id;
    private String compania;
    private String lugar;
    private String repartidor;
    private String cargador;
    private String observaciones;
    private List<XControlProductoDTO> productos;

}
