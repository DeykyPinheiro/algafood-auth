package com.algaworks.algafoodauth.dto;

import com.algaworks.algafoodauth.model.app.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// criando um user para ser reconhecido pelo userDetails
@Getter
public class AuthUserDto extends User {

    private String fullName;

    public AuthUserDto(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());

        this.fullName = usuario.getEmail();
    }

}
