package com.example.loja.model.categoria;

import com.example.loja.model.produto.ProdutoModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "tb_categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoriaModel implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    @ManyToMany(mappedBy = "categorias")
    private Set<ProdutoModel> produtos = new HashSet<>();
    public CategoriaModel(String nome) {
        this.nome = nome;
    }
}
