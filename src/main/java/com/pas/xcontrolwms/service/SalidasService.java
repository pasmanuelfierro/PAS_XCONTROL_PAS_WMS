package com.pas.xcontrolwms.service;

import com.pas.xcontrolwms.client.XControlClient;
import com.pas.xcontrolwms.dto.xcontrol.XControlRequest;
import com.pas.xcontrolwms.dto.xcontrol.salidas.XControlSalidasResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SalidasService {

    private final XControlClient XControlClient;

    public SalidasService(XControlClient xControlClient) {

        this.XControlClient = xControlClient;
    }

    public XControlSalidasResponse getSalidas(XControlRequest xControlRequest) {
        try {

            XControlSalidasResponse xControlSalidasResponse = XControlClient.salidasXControl(xControlRequest);

            return xControlSalidasResponse;
        } catch (Exception e) {
            log.error("error uploadDocument");
            throw new RuntimeException(e);
        }
    }
}
