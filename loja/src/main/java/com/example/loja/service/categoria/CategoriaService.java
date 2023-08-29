package com.example.loja.service.categoria;

import com.example.loja.dtos.categoria.CategoriaDTO;
import com.example.loja.model.categoria.CategoriaModel;
import com.example.loja.repositories.CategoriaRepository;
import com.example.loja.service.exceptions.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Transactional
    public CategoriaDTO save(CategoriaDTO categoriaDTO){
        categoriaDTO.setId(null);
        ModelMapper modelMapper = new ModelMapper();
        CategoriaModel categoriaModel = modelMapper.map(categoriaDTO, CategoriaModel.class);
        categoriaModel = repository.save(categoriaModel);
        categoriaDTO.setId(categoriaModel.getId());
        return categoriaDTO;
    }
    @Transactional(readOnly = true)
    public Page<CategoriaDTO> findAllPaged(PageRequest pageRequest){
        Page<CategoriaModel> categorias = repository.findAll(pageRequest);
        return categorias
                .map(categoria -> new ModelMapper().map(categoria, CategoriaDTO.class));
    }
    @Transactional(readOnly = true)
    public Optional<CategoriaDTO> findById(Long id){
        Optional<CategoriaModel> categoriaModel = repository.findById(id);
        CategoriaDTO categoriaDTO = new ModelMapper().map(categoriaModel.get(), CategoriaDTO.class);
        return Optional.of(categoriaDTO);
    }
    public void deleteById(Long id){
        Optional<CategoriaModel> categoriaModel = repository.findById(id);
        if (categoriaModel.isEmpty()){
            throw new EntityNotFoundException("Not found");
        }
        repository.deleteById(id);
    }
    @Transactional
    public CategoriaDTO putById(Long id, CategoriaDTO categoriaDTO){
        categoriaDTO.setId(id);
        ModelMapper modelMapper = new ModelMapper();
        CategoriaModel categoriaModel = modelMapper.map(categoriaDTO, CategoriaModel.class);
        repository.save(categoriaModel);
        return categoriaDTO;
    }
}
