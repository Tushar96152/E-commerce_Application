package org.ShopingApp.SpringStarter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity

public class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)    
     private long  id;
     
     private String Name;
     
     @ManyToOne(fetch = FetchType.LAZY) // joining the tables
     @JoinColumn(name = "category_id", referencedColumnName = "category_id")
     private Category category;
     
     private double price;
     private double weight;
     private String description;
     private String imageName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Product(long id, String name, Category category, double price, double weight, String description,
			String imageName) {
		super();
		this.id = id;
		Name = name;
		this.category = category;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imageName = imageName;
	}
	public Product() {
		super();
	}
	
	
                                                                                                                                                                                                                                     
     
}
