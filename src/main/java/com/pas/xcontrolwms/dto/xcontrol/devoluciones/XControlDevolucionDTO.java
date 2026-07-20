package com.pas.xcontrolwms.dto.xcontrol.devoluciones;

import com.pas.xcontrolwms.dto.xcontrol.XControlProductoDTO;
import lombok.Data;

import java.util.List;

@Data
public class XControlDevolucionDTO {

    private Long id;
    private Long valeId;
    private String compania;
    private String lugar;
    private String repartidor;
    private String cargador;
    private String motivo;
    private Integer turno;
    private Integer tipo;
    private List<XControlProductoDTO> productos;
}
