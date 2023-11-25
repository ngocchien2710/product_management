package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import com.vti.entity.Product;
import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;

public interface IProductService  {
	public Page<Product> getAllProducts(Pageable pageable, String search);

	public Product getProductById(short id);

	public Product createNewProduct(ProductFormForCreating productNewForm);

	public Product updateProduct(short id, ProductFormForUpdating productUpdateForm);

	public void deleteProductById(short id);

	Page<Product> getAllProducts(Pageable pageable);

	public boolean existsByName(String name);

	public int deleteManyProducts(List<Short> deleteList);


}
