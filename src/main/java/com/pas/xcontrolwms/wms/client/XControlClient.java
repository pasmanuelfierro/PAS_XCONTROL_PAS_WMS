package com.pas.xcontrolwms.wms.client;

import com.pas.xcontrolwms.xcontrolmapping.XControlRequest;
import com.pas.xcontrolwms.xcontrolmapping.devoluciones.XControlDevolucionResponse;
import com.pas.xcontrolwms.xcontrolmapping.entradas.XControlEntradasResponse;
import com.pas.xcontrolwms.xcontrolmapping.salidas.XControlSalidasResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class XControlClient {

    private final WebClient xcontrolWebClient;

    public XControlClient(@Qualifier("xcontrolWebClient") WebClient xcontrolWebClient) {
        this.xcontrolWebClient = xcontrolWebClient;
    }


    public XControlSalidasResponse salidasXControl(XControlRequest xControlRequest) {
        try {

            return xcontrolWebClient.get()
                    .uri("despachados/" + xControlRequest.getFecha())
                    .retrieve()
                    .bodyToMono(XControlSalidasResponse.class)
                    .block();

        } catch (Exception e) {
            log.error("error salidasXControl", e);
            throw new RuntimeException(e);
        }
    }

    public XControlEntradasResponse entradasXControl(XControlRequest xControlRequest) {
        try {

            return xcontrolWebClient.get()
                    .uri("compras/" + xControlRequest.getFecha())
                    .retrieve()
                    .bodyToMono(XControlEntradasResponse.class)
                    .block();
        } catch (Exception e) {
            log.error("error entradasXControl", e);
            throw new RuntimeException(e);
        }
    }


    public XControlDevolucionResponse devolucionXControl(XControlRequest xControlRequest) {
        try {

            return xcontrolWebClient.get()
                    .uri("devoluciones/" + xControlRequest.getFecha())
                    .retrieve()
                    .bodyToMono(XControlDevolucionResponse.class)
                    .block();
        } catch (Exception e) {
            log.error("error devolucionXControl", e);
            throw new RuntimeException(e);
        }
    }
}
