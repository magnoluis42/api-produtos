package com.example.loja.service.produto;

import com.example.loja.dtos.categoria.CategoriaDTO;
import com.example.loja.dtos.produto.ProdutoDTO;
import com.example.loja.model.categoria.CategoriaModel;
import com.example.loja.model.produto.ProdutoModel;
import com.example.loja.repositories.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Transactional
    public ProdutoDTO save(ProdutoDTO produtoDTO){
        produtoDTO.setId(null);
        ModelMapper modelMapper = new ModelMapper();
        ProdutoModel produtoModel = modelMapper.map(produtoDTO, ProdutoModel.class);
        produtoModel = repository.save(produtoModel);
        produtoDTO.setId(produtoModel.getId());
        return produtoDTO;
    }
    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAllPaged(PageRequest pageRequest){
        Page<ProdutoModel> produtos = repository.findAll(pageRequest);
        return produtos
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class));
    }

}
