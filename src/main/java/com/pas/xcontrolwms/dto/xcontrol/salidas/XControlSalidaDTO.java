package com.pas.xcontrolwms.dto.xcontrol.salidas;

import com.pas.xcontrolwms.dto.xcontrol.XControlProductoDTO;
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
