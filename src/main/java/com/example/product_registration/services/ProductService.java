package com.example.product_registration.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_registration.exceptions.NotFoundException;
import com.example.product_registration.model.Product;
import com.example.product_registration.repository.ProductRepository;

@Service
public class ProductService {


    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository repository;

    public List<Product> findAll() {
        logger.info("Buscando todos os produtos...");

        return repository.findAll();
    }

    public Product findById(Long id) {
        logger.info("Procurando produto com id: {}", id);

        return repository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado!"));
    }

    public Product Create(Product product) {
        logger.info("Criando produto: {}", product.getProductName());

        return repository.save(product);
    }

    public Product Update(Long id, Product product) throws NotFoundException {
        logger.info("Atualizando produto com id: {}", id);

        Product productExisting = findById(id);
        if (productExisting == null) {
            throw new NotFoundException("Nenhum dado encontrado para o id: " + id);
        }

        productExisting.setProductName(product.getProductName());
        productExisting.setDescription(product.getDescription());
        productExisting.setPrice(product.getPrice());

        return repository.save(productExisting);
    }

    public void Delete(Long id) {
        logger.info("Deletando produto com id: {}", id);

        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado!"));
        repository.delete(entity);
    }
}