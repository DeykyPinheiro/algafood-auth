package com.algaworks.algafoodauth.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated //o spring auto valida a classe
@ConfigurationProperties("auth.datasource")
public class FlywayProperties {

    @NotBlank
    String jdbcUrl;

    @NotBlank
    String username;

    @NotBlank
    String password;
}
