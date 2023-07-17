package com.algaworks.algafoodauth.common.config.db;

import com.algaworks.algafoodauth.repository.app.UsuarioRepository;
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

//configuracao do banco de dados da applicacao
@Configuration
@EnableJpaRepositories(basePackageClasses = UsuarioRepository.class, entityManagerFactoryRef = "appEntityManager")
public class AppDbconfig {

    //    isso é pra substituir o dataSource quem vem padrao dentro do jpa
    @Bean
    @ConfigurationProperties("app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    //    aqui é onde eu mostro qual DataSoucer usar
    @Bean
//    @Primary
    public LocalContainerEntityManagerFactoryBean appEntityManager(
            EntityManagerFactoryBuilder builder, @Qualifier("appDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).
                packages("com.algaworks.algafoodauth.model.app")
                .build();
    }
}
