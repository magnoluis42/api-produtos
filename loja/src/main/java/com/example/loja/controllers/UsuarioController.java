package com.example.loja.controllers;

import com.example.loja.controllers.dto.usuario.UsuarioRequest;
import com.example.loja.controllers.dto.usuario.UsuarioResponse;
import com.example.loja.dtos.usuario.UsuarioDTO;
import com.example.loja.service.usuario.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Validated UsuarioRequest usuarioRequest){
       var modelMapper = new ModelMapper();
       UsuarioDTO usuarioDTO = modelMapper.map(usuarioRequest, UsuarioDTO.class);
       usuarioDTO = service.save(usuarioDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(usuarioRequest.getId()).toUri();
        return new ResponseEntity<>(modelMapper.map(usuarioDTO, UsuarioResponse.class), HttpStatus.CREATED);
    }
}
