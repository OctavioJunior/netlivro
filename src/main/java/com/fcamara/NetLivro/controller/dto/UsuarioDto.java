package com.fcamara.NetLivro.controller.dto;

import com.fcamara.NetLivro.model.Usuario;

public class UsuarioDto {
    private Long id;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
