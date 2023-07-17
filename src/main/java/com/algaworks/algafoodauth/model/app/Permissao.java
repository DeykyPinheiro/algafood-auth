package com.algaworks.algafoodauth.model.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;


    @ManyToMany(mappedBy = "listaPermissao")
    private Set<Grupo> listaGrupos;

    public Permissao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

}
