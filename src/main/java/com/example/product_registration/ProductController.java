package com.example.product_registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_registration.exceptions.NotFoundException;
import com.example.product_registration.model.Product;
import com.example.product_registration.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findById(@PathVariable Long id) throws NotFoundException {

		return productService.findById(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findAll() {
		return productService.findAll();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Product Create(@RequestBody Product product) throws NotFoundException {
		return productService.Create(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> Update(@PathVariable Long id, @RequestBody Product product)
			throws NotFoundException {
		Product updateProduct = productService.Update(id, product);
		return ResponseEntity.ok(updateProduct);
	}

	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Long id) throws NotFoundException {
		productService.Delete(id);
	}

}
