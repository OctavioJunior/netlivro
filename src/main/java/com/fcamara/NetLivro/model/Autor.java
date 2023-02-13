package com.fcamara.NetLivro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "autor")
    private List<Livro> livro = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro( List<Livro> livro ) {
        this.livro = livro;
    }
}
