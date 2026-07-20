package com.pas.xcontrolwms.dto.xcontrol;

import lombok.Data;

@Data
public class XControlRequest {

    private String fecha;

    public XControlRequest(String fecha) {
        this.fecha = fecha;
    }

}
