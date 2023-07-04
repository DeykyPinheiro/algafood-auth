package com.algaworks.algafoodauth;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.time.Duration;
import java.util.UUID;


// Resource Owner Password Credentials Grant ou Password Credentials grant ou Password Flow ou Password Grant Type
//EVITAR USAR ESSE FLUXO


@Configuration
//@Import(OAuth2AuthorizationServerConfiguration.class)
@EnableWebSecurity
//@EnableAuthorizationServer // nao funciona dai tennho que fazer assim @Import(OAuth2AuthorizationServerConfiguration.class)
//mudei grande parte do que vem no curso, pois a solucao foi deprecada, mas aqui estou fazendo o equivalente
//VOU TER QUE REESCREVER, POR ISSO TA COMENTADA
public class AuthorizationServerConfig {

    //    isso está sendo obrigatorio, mas no curso mudamos, entao vou deixar ai temporariamente
//    bean que guarda os clientes do server de autorizacao, implementa em memoria
    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
//esse é meu backend solicitando alguem token, a api, o meu resource server
        RegisteredClient registeredClient = RegisteredClient
                .withId("1")
                .clientId("algafood-backend") // identificacao do cliente(quem vai chamar esse servico)
                .clientSecret(passwordEncoder.encode("123"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC) //basica autentication nome:senha em base64
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // equivale ao password flow, é fluxo mais simples de autenticacao
                .scope("READ") // escopo de leitura apenas
//                configuracoes dos tokens
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED) // tem dois tipos, o reference é um token opaco e o JWT é o seltf_contained
                        .accessTokenTimeToLive(Duration.ofMinutes(30)) // duracao de 30 minutos dos tokens
                        .build())
                .build();

        //esse é algum cliente conhecido solicitando algum token
        RegisteredClient client = RegisteredClient
                .withId("2")
                .clientId("client")
                .clientSecret(passwordEncoder.encode("123"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("READ")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                        .accessTokenTimeToLive(Duration.ofMinutes(30))
                        .build())
                .build();

//        se eu precisar de mais um, só registrar um cliente e passar como pametro na funcao "InMemoryRegisteredClientRepository"
        return new InMemoryRegisteredClientRepository(registeredClient, client);
    }

    //    filtro do authorizationServer
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    //server pra ser o primeiro filtro a passar, depois segue o fluxo, isso pro login funcionar de maneira adequada
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        //aqui ele aplica as configuracoes padrao de seguraca, por algum motivo ta dando
//        erro, mas como nao tenho tempo, vou neglicenciar essa parte, vou deixar comentado
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http); // isso aqui só funciona
        return http.build();
    }

    //eles renomearam ProviderSettings  para AuthorizationServerSettings, mas em essencia é a mesma coisa
//    isso diz quem vai assinar os tokens, no local seria 8080
//    em prod seria o dns  da api
    //essa funcao diz quem é o authorization server que vai assinar os tokens, LEIA O EXEMPLO ACIMA
    @Bean
    public AuthorizationServerSettings serverSettings(AlgafoodSecurityProperties properties) {
//        System.out.println("estou dentro da classe: "+ properties.getProviderUrl()); // ta dando certo
        return AuthorizationServerSettings.builder()
                .issuer(properties.getProviderUrl()) // é a url do autorizationServer
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations,
                                                                 RegisteredClientRepository registeredClientRepository) {

        return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
    }

//    esse Bean serve para abrir o keystore e usar a chave privada pra assinar
    @Bean
    public JWKSource<SecurityContext> jwkSource(JwtKeyStoreProperties properties) throws Exception {
        char[] keyStorePass = properties.getPassword().toCharArray();
        String keyParAlias = properties.getKeypairAlias();

        Resource jksLocation = properties.getJksLocation();
        InputStream inputStream = properties.getJksLocation().getInputStream();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, keyStorePass);

        RSAKey rsaKey = RSAKey.load(keyStore, keyParAlias, keyStorePass);
        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }


}
