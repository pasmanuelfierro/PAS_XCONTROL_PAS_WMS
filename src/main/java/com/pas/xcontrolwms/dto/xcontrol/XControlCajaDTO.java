package com.pas.xcontrolwms.dto.xcontrol;

import lombok.Data;

import java.util.List;

@Data
public class XControlCajaDTO {

    private String codigo;
    private List<String> folios;

}