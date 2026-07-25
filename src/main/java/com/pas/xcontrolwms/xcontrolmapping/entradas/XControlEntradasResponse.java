package com.pas.xcontrolwms.xcontrolmapping.entradas;

import com.pas.xcontrolwms.xcontrolmapping.salidas.XControlSalidaDTO;
import lombok.Data;

import java.util.List;

@Data
public class XControlEntradasResponse {
    private List<XControlEntradaDTO> data;
}
