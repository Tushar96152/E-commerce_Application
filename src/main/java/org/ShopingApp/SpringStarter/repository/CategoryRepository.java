package org.ShopingApp.SpringStarter.repository;

import org.ShopingApp.SpringStarter.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
