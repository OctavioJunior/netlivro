package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.config.exception.InvalidRequestException;
import com.fcamara.NetLivro.config.exception.ResourceNotFoundException;
import com.fcamara.NetLivro.config.security.TokenService;
import com.fcamara.NetLivro.controller.form.EmprestimoForm;
import com.fcamara.NetLivro.model.Emprestimo;
import com.fcamara.NetLivro.repository.EmprestimoRepository;
import com.fcamara.NetLivro.repository.ExemplarRepository;
import com.fcamara.NetLivro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;


    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<Emprestimo> listarTodosEmprestimos(Boolean disponivel) {
        List<Emprestimo> emprestimos;

        if(disponivel == null) {
             emprestimos = emprestimoRepository.findAll();
        } else {
            emprestimos = emprestimoRepository.findByExemplarDisponivel(disponivel);
        }

        return emprestimos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Emprestimo> criarEmprestimo(HttpServletRequest req, @RequestBody @Valid EmprestimoForm form, UriComponentsBuilder uriBUilder) {
        String token = req.getHeader("Authorization").substring(7);
        Long usuarioId = tokenService.getIdUsuario(token);

        Emprestimo emprestimo = form.converter(usuarioId, usuarioRepository, exemplarRepository);
        emprestimoRepository.save(emprestimo);

        URI uri = uriBUilder.path("emprestimo/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimo);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity devolucao(@PathVariable Long id){
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        if(!emprestimo.isPresent()) throw new ResourceNotFoundException("empréstimo não encontrado");
        emprestimo.get().setDataDevolucao(LocalDate.now());
        emprestimo.get().getExemplar().setDisponivel(true);
//        emprestimoRepository.save(emprestimo.get());
//        exemplarRepository.save(emprestimo.get().getExemplar());
        return ResponseEntity.ok().build();
    }
}
