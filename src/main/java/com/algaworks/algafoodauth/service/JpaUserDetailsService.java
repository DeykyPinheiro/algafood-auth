package com.algaworks.algafoodauth.service;

import com.algaworks.algafoodauth.dto.AuthUserDto;
import com.algaworks.algafoodauth.model.app.Usuario;
import com.algaworks.algafoodauth.repository.app.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//essa classe é responsavel por buscar os usuarios em banco de dados, sem ela ele busca na memoria
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("Usuario não encontrado com esse e-mail!"));
//estou passando uma lista vazia por hora
        System.out.println("CHAMEI FINALMENTE loadUserByUsername");
        return new User(user.getNome(), user.getSenha(), Collections.emptyList());
    }
}
//http://localhost:9000/oauth2/authorize?client_id=client&redirect_uri=https%3A%2F%2Foauthdebugger.com%2Fdebug&scope=READ&response_type=code&response_mode=form_post&state=cce93xkg8tu&nonce=t0he77hnqgp
