package com.pas.xcontrolwms.xcontrolmapping;

import lombok.Data;

@Data
public class XControlRequest {

    private String fecha;

    public XControlRequest(String fecha) {
        this.fecha = fecha;
    }

}
