package com.algaworks.algafoodauth.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Oauth2Authorization {

    @Id
    private String id;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    private String authorizedScopes;
    private Blob attributes;
    private String state;
    private Blob authorizationCodeValue;
    private Timestamp authorizationCodeIssuedAt;
    private Timestamp authorizationCodeExpiresAt;
    @Lob
    private Blob authorizationCodeMetadata;
    private String accessTokenValue;
    private Timestamp accessTokenIssuedAt;
    private Timestamp accessTokenExpiresAt;
    private Blob accessTokenMetadata;
    private String accessTokenType;
    private String accessTokenScopes;
    private Blob oidcIdTokenValue;
    private Timestamp oidcIdTokenIssuedAt;
    private Timestamp oidcIdTokenExpiresAt;
    private Blob oidcIdTokenMetadata;
    private Blob refreshTokenValue;
    private Timestamp refreshTokenIssuedAt;
    private Timestamp refreshTokenExpiresAt;
    private Blob refreshTokenMetadata;
    private Blob userCodeValue;
    private Timestamp userCodeIssuedAt;
    private Timestamp userCodeExpiresAt;
    private Blob userCodeMetadata;
    private Blob deviceCodeValue;
    private Timestamp deviceCodeIssuedAt;
    private Timestamp deviceCodeExpiresAt;
    private Blob deviceCodeMetadata;
}
