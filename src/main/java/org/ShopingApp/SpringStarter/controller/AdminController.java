package org.ShopingApp.SpringStarter.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.ShopingApp.SpringStarter.dto.ProductDTO;
import org.ShopingApp.SpringStarter.model.Category;
import org.ShopingApp.SpringStarter.model.Product;
import org.ShopingApp.SpringStarter.service.CategoryService;
import org.ShopingApp.SpringStarter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {

	private static final Object ProductDTO = null;
	public String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productImages";
	                 //fetching the path of file
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@GetMapping("/admin")
	public String adminHome()
	{
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model)
	{   
	    model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCategoriesAdd(Model model)
	{   model.addAttribute("category",new Category());
		return  "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postCategoriesAdd(@ModelAttribute("category") Category category)
	{   categoryService.addCategory(category);
		return  "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id)
	{
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategoryById(@PathVariable int id,Model model)
	{
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent())
		{
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		else
		return "404";
	}
	
	//product Section
	@GetMapping("/admin/products")
	public String getProducts(Model model)
	{
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model)
	{
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("ProductDTO")ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName")String imgName) throws IOException{
		
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setDescription(productDTO.getDescription());
		
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID = file.getOriginalFilename(); //it gives file original name
			java.nio.file.Path fileNameAndPath =  Paths.get(uploadDir, imageUUID); //crating the file path and name
		    Files.write(fileNameAndPath, file.getBytes());
		}
		else
		{
			imageUUID = imgName;
			
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id)
	{
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/update/{id}")
	public String updateProductById(@PathVariable long id,Model model)
	{
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		System.out.println(productDTO.getCategoryId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("productDTO",productDTO);
			return "productsAdd";
	
	}
	
}
