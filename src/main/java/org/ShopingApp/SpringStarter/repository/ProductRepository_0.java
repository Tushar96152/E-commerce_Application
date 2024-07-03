package org.ShopingApp.SpringStarter.repository;

import java.util.List;

import org.ShopingApp.SpringStarter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositor extends JpaRepository<Product,Long> {

	List<Product> findAllByCategory_id(long id);
}
