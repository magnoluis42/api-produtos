package com.example.loja.model.usuarios;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private FuncaoUsuario funcaoUsuario;
    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_funcao", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "funcao_id"))
    private Set<FuncaoUsuario> funcaoUsuarios = new HashSet<>();
     */
}
