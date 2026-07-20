package com.pas.xcontrolwms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${xcontrol.base-url}")
    String XCONTROL_BASE_URL;

    @Value("${xcontrol.auth}")
    String authorization;

    @Bean
    public WebClient xcontrolWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(XCONTROL_BASE_URL)
                .codecs(configurer ->
                        configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
                )
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        authorization)
                .build();
    }
}
