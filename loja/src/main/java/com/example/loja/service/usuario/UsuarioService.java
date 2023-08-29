package com.example.loja.service.usuario;

import com.example.loja.dtos.usuario.UsuarioDTO;
import com.example.loja.model.usuarios.UsuarioModel;
import com.example.loja.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO){
        usuarioDTO.setId(null);
        ModelMapper modelMapper = new ModelMapper();
        UsuarioModel usuarioModel = modelMapper.map(usuarioDTO, UsuarioModel.class);
        usuarioModel.setSenha(encoder.encode(usuarioDTO.getSenha()));
        usuarioModel = repository.save(usuarioModel);
        usuarioDTO.setId(usuarioModel.getId());
        return usuarioDTO;
    }
}
