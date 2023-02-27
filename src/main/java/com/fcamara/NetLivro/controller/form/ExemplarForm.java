package com.fcamara.NetLivro.controller.form;

public class ExemplarForm {
    private Long livroId;

    public Long getLivroId() {
        return livroId;
    }

//    public Exemplar converterExemplar(LivroRepository livroRepository){
//        Optional<Livro> livro = livroRepository.findById(livroId);
//        return new Exemplar(livro);
//    }
}
