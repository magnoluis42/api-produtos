package com.example.loja.controllers.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse{
    private Long id;
    private String nome;
    private String imagem;
    private Double preco;
}