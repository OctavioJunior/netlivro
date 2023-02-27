package com.fcamara.NetLivro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "exemplar")
    private List<Emprestimo> emprestimo = new ArrayList<>();
    @ManyToOne @JsonIgnore
    private Livro livro;

    private boolean disponivel = true;

    public Exemplar() {
    }

    public Exemplar(Livro livro) {
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(List emprestimo) {
        this.emprestimo = emprestimo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
