package com.algaworks.algafoodauth.repository.app;

import com.algaworks.algafoodauth.model.app.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
}
