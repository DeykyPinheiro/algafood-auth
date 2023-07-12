package com.algaworks.algafoodauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //dentro dela tem o configuration, mas to deixando ai pra ficar mais explicito
public class WebSecurityConfig {

//    vou comentar pq nao vou mais usar eles, vou pegar do banco

    @Autowired
    PasswordEncoder passwordEncoder;

    //salvar dois usuario em memoria e codificar com Bcript
//    simular os usuarios em banco de dados
//    @Bean
//    public UserDetailsService users() {
//        // O construtor garantirá que as senhas sejam codificadas antes de salvar na memória em bcript
//        UserDetails admin = User
//                .withUsername("deyky")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER")
//                .build();
//        UserDetails user = User
//                .withUsername("user")
//                .password(passwordEncoder.encode("123"))
//                .roles("READ")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

}