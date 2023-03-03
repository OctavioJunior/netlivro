package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.exception.ConflictException;
import com.fcamara.NetLivro.exception.ResourceNotFoundException;
import com.fcamara.NetLivro.controller.dto.UsuarioDto;
import com.fcamara.NetLivro.controller.form.UsuarioForm;
import com.fcamara.NetLivro.model.Usuario;
import com.fcamara.NetLivro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Optional<Usuario> usedEmailUser = usuarioRepository.findByEmail(form.getEmail());
        if(usedEmailUser.isPresent()) throw new ConflictException("este e-mail já está em uso");

        Usuario usuario = form.converter();

        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()) throw new ResourceNotFoundException("usuário não encontrado");

        usuarioRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
