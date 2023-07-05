package com.algaworks.algafoodauth.repository.auth;

import com.algaworks.algafoodauth.model.auth.Oauth2Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Oauth2AuthorizationRepository extends JpaRepository<Oauth2Authorization, String> {

}
