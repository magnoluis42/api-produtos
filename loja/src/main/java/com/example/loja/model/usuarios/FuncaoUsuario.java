package com.example.loja.model.usuarios;

public enum FuncaoUsuario{
    ADMIN("admin"),
    USUARIO("usuario");
    private String funcao;
    FuncaoUsuario(String funcao) {
        this.funcao = funcao;
    }
    public String getFuncao() {
        return funcao;
    }
}

