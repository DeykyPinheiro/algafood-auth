package com.algaworks.algafoodauth.common.config.flyway;

import com.algaworks.algafoodauth.properties.FlywayProperties;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//isso só foi necessario pq tenho mais de um banco conectado
@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(FlywayProperties properties) {
        // Configurar as propriedades do banco de dados
        String jdbcUrl = properties.getJdbcUrl();
        String username = properties.getUsername();
        String password = properties.getPassword();

        // Configurar o Flyway
        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, username, password)
                .load();

        // Executar as migrações
        flyway.migrate();

        return flyway;
    }
}
