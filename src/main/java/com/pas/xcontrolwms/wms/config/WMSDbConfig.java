package com.pas.xcontrolwms.wms.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.pas.xcontrolwms.wms.repository",
        entityManagerFactoryRef = "wmsEntityManagerFactory",
        transactionManagerRef = "wmsTransactionManager"
)
public class WMSDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.wms")
    public DataSource wmsDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean wmsEntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return builder
                .dataSource(wmsDataSource())
                .packages("com.pas.xcontrolwms.wms.model")
                .persistenceUnit("wms")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager wmsTransactionManager(
            @Qualifier("wmsEntityManagerFactory")
            EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}