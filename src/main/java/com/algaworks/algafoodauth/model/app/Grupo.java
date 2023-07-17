package com.algaworks.algafoodauth.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "grupo_permissao",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> listaPermissao;


    @ManyToMany(mappedBy = "listaGrupos")
    private List<Usuario> listaUsuario;
}
