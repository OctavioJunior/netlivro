package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.model.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AutorForm {
@NotNull @NotEmpty @Length(min = 5)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public Autor converter(){
        return new Autor(nome);
    }
}

