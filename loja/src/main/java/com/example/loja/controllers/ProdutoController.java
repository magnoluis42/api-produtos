package com.example.loja.controllers;

import com.example.loja.controllers.dto.categoria.CategoriaRequest;
import com.example.loja.controllers.dto.categoria.CategoriaResponse;
import com.example.loja.controllers.dto.produto.ProdutoRequest;
import com.example.loja.controllers.dto.produto.ProdutoResponse;
import com.example.loja.dtos.categoria.CategoriaDTO;
import com.example.loja.dtos.produto.ProdutoDTO;
import com.example.loja.service.produto.ProdutoService;
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


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> save(@RequestBody ProdutoRequest produtoRequest){
        ModelMapper modelMapper = new ModelMapper();
        ProdutoDTO produtoDTO = modelMapper.map(produtoRequest, ProdutoDTO.class);
        produtoDTO = service.save(produtoDTO);
        return new ResponseEntity<>(modelMapper.map(produtoDTO, ProdutoResponse.class), HttpStatus.CREATED);
    }
/*
    @PostMapping
    public ResponseEntity<ProdutoModel> novoProduto(@RequestBody @Validated ProdutoDTO criarProdutoDTO){
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(criarProdutoDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(produtoModel));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable (value = "id") Long id){
        Optional<ProdutoModel> produto = service.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. ");
        }
        service.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluido. ");
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable (value = "id") Long id,
                                                   @RequestBody ProdutoDTO criarProdutoDTO){
        Optional<ProdutoModel> produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. ");
        }
        var produtoModel = produto.get();
        BeanUtils.copyProperties(criarProdutoDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(produtoModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        Optional<ProdutoModel> produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado. ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }
/*
    @GetMapping
    public ResponseEntity<Object> findAll(){
        List<ProdutoModel> produtos = repository.findAll();
        if(produtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto encontrado. ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }
 */

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "linhasPorPaginas", defaultValue = "10") Integer linhasPorPagina,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
            @RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor
    ){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), ordenarPor);
        Page<ProdutoDTO> produtos = service.findAllPaged(pageRequest);
        ModelMapper modelMapper = new ModelMapper();
        Page<ProdutoResponse> response = produtos
                .map(produto -> modelMapper.map(produto, ProdutoResponse.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
