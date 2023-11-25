package com.vti.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vti.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Short>, JpaSpecificationExecutor<Product>{
	
	public boolean existsByName(String name);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Product WHERE id IN(:ids)")// hql
	public int deleteManyProducts(@Param("ids") List<Short> isDeleteList);
	
	

}
