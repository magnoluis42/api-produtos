package com.example.loja.controllers;

import com.example.loja.controllers.dto.categoria.CategoriaRequest;
import com.example.loja.controllers.dto.categoria.CategoriaResponse;
import com.example.loja.dtos.categoria.CategoriaDTO;
import com.example.loja.service.categoria.CategoriaService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaResponse> save(@RequestBody CategoriaRequest categoriaRequest){
        ModelMapper modelMapper = new ModelMapper();
        CategoriaDTO categoriaDTO = modelMapper.map(categoriaRequest, CategoriaDTO.class);
        categoriaDTO = service.save(categoriaDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(categoriaRequest.getId()).toUri();
        return new ResponseEntity<>(modelMapper.map(categoriaDTO, CategoriaResponse.class), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPaginas", defaultValue = "10") Integer linhasPorPagina,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
            @RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor
    ){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), ordenarPor);
        Page<CategoriaDTO> categorias = service.findAllPaged(pageRequest);
        ModelMapper modelMapper = new ModelMapper();
        Page<CategoriaResponse> response = categorias
                .map(categoria -> modelMapper.map(categoria, CategoriaResponse.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoriaResponse>> findById(@PathVariable (value = "id") Long id){
        Optional<CategoriaDTO> list = service.findById(id);
        CategoriaResponse categoriaResponse = new ModelMapper()
                .map(list, CategoriaResponse.class);
        return new ResponseEntity<>(Optional.of(categoriaResponse), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> putById(@RequestBody CategoriaRequest categoriaRequest, @PathVariable Long id){
        ModelMapper modelMapper = new ModelMapper();
        CategoriaDTO categoriaDTO = modelMapper.map(categoriaRequest, CategoriaDTO.class);
        categoriaDTO = service.putById(id, categoriaDTO);
        return new ResponseEntity<>(modelMapper.map(categoriaDTO, CategoriaResponse.class), HttpStatus.OK);
    }

}
