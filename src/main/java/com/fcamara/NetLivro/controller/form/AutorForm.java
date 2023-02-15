package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.model.Autor;
import com.fcamara.NetLivro.repository.AutorRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

    public Autor atualizar(Long id, AutorRepository repository) {
        Autor autor = repository.getReferenceById(id);

        autor.setNome(this.nome);

        return autor;
    }
}

