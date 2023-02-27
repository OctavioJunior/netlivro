package com.fcamara.NetLivro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Emprestimo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEmprestimo = LocalDate.now();
    private  LocalDate dataDevolucao;
    @ManyToOne @JsonIgnore
    private Usuario usuario;
    @ManyToOne @JsonIgnore
    private Exemplar exemplar;
}
