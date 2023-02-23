package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UsuarioForm {

    @NotNull @NotEmpty @Email
    private String email;

    @NotNull @NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "A senha deve ter pelo menos 8 caracteres e conter uma letra maiúscula, minúscula, um número e caracter especial")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converter() {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(senha);
        return new Usuario(email, senhaCriptografada);
    }
}
