package com.pas.xcontrolwms.scheduler;

import com.pas.xcontrolwms.dto.xcontrol.XControlRequest;
import com.pas.xcontrolwms.service.SalidasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {
    @Autowired
    private SalidasService salidasService;

    // @Scheduled(cron = "0 30 5,17 * * ?") //LOCAL
    // @Scheduled(cron = "0 */2 * * * ?") //LOCAL CADA 2 MIN
    // @Scheduled(cron = "0 30 4,16 * * ?") // PROD
    // @Scheduled(cron = "0 30 3,15 * * ?") // QA

    @Scheduled(cron = "0 * * * * ?")
    //@Scheduled(cron = "0 50 23 * * ?")
    public void getSalidasScheduler() {
        log.info("Inicio del getSalidasScheduler {}", OffsetDateTime.now());
        String fecha = LocalDate.now(ZoneId.of("America/Mexico_City")).toString();
        XControlRequest fechaXControl = new XControlRequest(fecha);
        salidasService.getSalidas(fechaXControl);

        log.info("Termina del getSalidasScheduler {}", OffsetDateTime.now());
    }

}