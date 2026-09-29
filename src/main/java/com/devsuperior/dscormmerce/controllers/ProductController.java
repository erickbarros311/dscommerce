package com.devsuperior.dscormmerce.controllers;

import com.devsuperior.dscormmerce.dto.ProductDTO;
import com.devsuperior.dscormmerce.entities.Product;
import com.devsuperior.dscormmerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service; // depende de service

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findBiId(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto); //customizando uma resposta de rwquisição
    }

    //chama o service para buscar os registros
    //para páginar a consulta coloque no findAll Pageable
    //atualizado para consultas pelo nome do produto (Ver a query JPQL no ProductRepository)
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
           @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable){
        Page<ProductDTO> dto = service.findAll(name, pageable);
                return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto){ //pra pegar o corpo JSON
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
        }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @PathVariable Long id, @RequestBody ProductDTO dto){
        dto = service.update(id,dto);
        return ResponseEntity.ok(dto); //customizando uma resposta de rwquisição
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}

// para buscar sem paginar
//    @GetMapping
//    public List<ProductDTO> findAll(Pageable pageable){
//        return service.findAll(pageable);
//    }


// PRIMEIRA CONFIGURAÇÃO PARA TESTE PELO REPOSITORY
// private ProductRepository repository;

//    @GetMapping
//    public String teste(){
//        Optional<Product> result = repository.findById(1L);
//        Product product = result.get();
//        return product.getNome();
//
//    }





