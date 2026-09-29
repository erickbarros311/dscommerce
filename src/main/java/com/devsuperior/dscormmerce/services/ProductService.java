package com.devsuperior.dscormmerce.services;

import com.devsuperior.dscormmerce.dto.ProductDTO;
import com.devsuperior.dscormmerce.entities.Product;
import com.devsuperior.dscormmerce.repositories.ProductRepository;
import com.devsuperior.dscormmerce.services.exceptions.DatabaseException;
import com.devsuperior.dscormmerce.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService{

    //injetou a interace product repository
    @Autowired
    private ProductRepository repository;

    // transaction do Spring frameworl
    @Transactional(readOnly = true)// como tá apenas buscando da um lock no BD para ser mais rapido
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    // Busca todos os registros
    // usou de forma redudiza como explicado na aula da busca por is (Criando DTO e estrurando)
    // muda de lista para Page
    // por padrão vai retornar 20 elementos
    // personalizando no browser: http://localhost:8080/products?size=12&page=0&sort=name
    // terminou assim: http://localhost:8080/products?size=12&page=0&sorte=name,desc
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable){
        Page<Product> result = repository.searchByName(name, pageable);
        return result.map(x -> new ProductDTO(x));
    }

    //SALVAR
    @Transactional()
    public ProductDTO insert(ProductDTO dto){
        //instanciou
        Product entity = new Product();
        //copiou os dados do product pra cá
        copyDtoToEntity(dto, entity);
        //mandou salvar
        entity = repository.save(entity);
        //retorna um novo objeto salvo com os dados da entity
        return new ProductDTO(entity);
    }


    //UPDATE
    @Transactional()
    public ProductDTO update(Long id, ProductDTO dto){
       try {
         //instanciou o prod. apenas com a ref do argumento sem ir no banco
        Product entity = repository.getReferenceById(id);

       copyDtoToEntity(dto, entity);

        //mandou salvar
        entity = repository.save(entity);

        //retorna um novo objeto salvo com os dados da entity
        return new ProductDTO(entity);
       } catch (EntityNotFoundException e){
           throw new ResourceNotFoundException("Recurso não encotnrado no banco.");
       }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encotnrado no banco.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial.");
        }

    }


    //metodo para evitar a duplicacao de codigo entre insert e update
    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setNome(dto.getName());
        entity.setNome(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }

    }

// buscar com Lista
//@Transactional(readOnly = true)
//public List<ProductDTO> findAll(){
//    List<Product> result = repository.findAll();
//    return result.stream().map(x -> new ProductDTO(x)).toList();
//}


//@Transactional(readOnly = true)// como tá apenas buscando da um lock no BD para ser mais rapido
//public ProductDTO findById(Long id){
//    Optional<Product> result = repository.findById(id);
//    Product product = result.get();
//    ProductDTO dto = new ProductDTO(product);
//    return dto;
//}


//era assim até execeções customizadas
//@Transactional(readOnly = true)
//public Page<ProductDTO> findAll(Pageable pageable){
//    Page<Product> result = repository.findAll(pageable);
//    return result.map(x -> new ProductDTO(x));
//}
