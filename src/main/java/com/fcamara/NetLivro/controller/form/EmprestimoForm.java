package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.exception.ConflictException;
import com.fcamara.NetLivro.exception.InvalidRequestException;
import com.fcamara.NetLivro.model.Emprestimo;
import com.fcamara.NetLivro.model.Exemplar;
import com.fcamara.NetLivro.model.Usuario;
import com.fcamara.NetLivro.repository.ExemplarRepository;
import com.fcamara.NetLivro.repository.UsuarioRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class EmprestimoForm {

    @NotNull
    private Long exemplarId;

    public Long getExemplarId() {
        return exemplarId;
    }

    public Emprestimo converter(Long usuarioId, UsuarioRepository usuarioRepository, ExemplarRepository exemplarRepository) {
        Optional<Exemplar> exemplar = exemplarRepository.findById(exemplarId);
        if(!exemplar.isPresent()) throw new InvalidRequestException("exemplar não encontrado");
        if(!exemplar.get().isDisponivel()) throw new ConflictException("este exemplar já foi emprestado a outro usuário");

        Usuario usuario = usuarioRepository.getReferenceById(usuarioId);

        exemplar.get().setDisponivel(false);
        exemplarRepository.save(exemplar.get());

        return new Emprestimo(usuario, exemplar.get());
    }

}
