package com.signup.projetoAPIsignup.usuarioCheck;

public class UsuarioCredenciais {
    private Integer id;

    private String nome;

    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
