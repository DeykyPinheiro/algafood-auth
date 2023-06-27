package com.algaworks.algafoodauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;

import java.util.UUID;

@Configuration
@Import(OAuth2AuthorizationServerConfiguration.class)
//@EnableAuthorizationServer // nao funciona dai tennho que fazer assim @Import(OAuth2AuthorizationServerConfiguration.class)
//mudei grande parte do que vem no curso, pois a solucao foi deprecada, mas aqui estou fazendo o equivalente
//VOU TER QUE REESCREVER, POR ISSO TA COMENTADA
public class AuthorizationServerConfig {

//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("algafood-web") // identificacao do cliente
//                .clientSecret("{noop}secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // equivale ao password, só que foi deprecado
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) //
//                .redirectUri("http://127.0.0.1:8081/login/oauth2/code/articles-client-oidc")
//                .redirectUri("http://127.0.0.1:8081/authorized")
//                .scope(OidcScopes.OPENID)
//                .scope("read") // escopo de leitura apenas
//                .build();
//
////        se eu precisar de mais um, só registrar um cliente e passar como pametro na funcao "InMemoryRegisteredClientRepository"
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }


}
