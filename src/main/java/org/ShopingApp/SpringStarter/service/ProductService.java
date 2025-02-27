package org.ShopingApp.SpringStarter.service;

import java.util.List;
import java.util.Optional;

import org.ShopingApp.SpringStarter.model.Product;
import org.ShopingApp.SpringStarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct()
	{
		return productRepository.findAll();
	}
	public void addProduct(Product product)
	{
		productRepository.save(product);
		
	}
	public void removeProductById(long id)
	{
		productRepository.deleteById(id);
	}
	public Optional<Product> getProductById(long id)
	{
		return productRepository.findById(id);
	}
	public List<Product> getAllProductsByCategoryId(long id)
	{
		return productRepository.findAllByCategory_id(id);
	}
	
	
}
