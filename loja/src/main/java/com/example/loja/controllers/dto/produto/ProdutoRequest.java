package com.example.loja.controllers.dto.produto;

import com.example.loja.model.categoria.CategoriaModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest{
    private String nome;
    private String imagem;
    private Double preco;
    private Set<CategoriaModel> categorias = new HashSet<>();
}
