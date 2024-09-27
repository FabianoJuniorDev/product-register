package com.example.product_registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product_registration.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
