package com.algaworks.algafoodauth.common.config.db;

import com.algaworks.algafoodauth.repository.app.UsuarioRepository;
import com.algaworks.algafoodauth.repository.auth.Oauth2AuthorizationRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

//configuracao do banco de dados de autenticacao
@Configuration
//primeiro é de qual pacote pedar a referencia, segundo é qual entity maneage usar
@EnableJpaRepositories(basePackageClasses = Oauth2AuthorizationRepository.class, entityManagerFactoryRef = "authEntityManager")
public class AuthDbconfig {


    @Bean
    @Primary
    @ConfigurationProperties("auth.datasource")
    public DataSource authDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean authEntityManager(
            EntityManagerFactoryBuilder builder, @Qualifier("authDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).
                packages("com.algaworks.algafoodauth.model.auth")
                .build();
    }
}
