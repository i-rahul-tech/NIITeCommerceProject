package com.spring.example.BackendPrac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.spring.example.BackendPrac.entity.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Product getProductById(int id) {
		Product product = manager.find(Product.class,  id);
		return product;
	}

	@Override
	public void saveProduct(Product product) {
		manager.persist(product);
		
	}

	@Override
	public List<Product> getAllProduct() {
		Query query = (Query)manager.createQuery("from Product p");
		List<Product> list = (List<Product>)query.getResultList();
		System.out.println(list.size());
		for(Product pro:list)
		{
			System.out.println("Product Id: "+pro.getProductId()+" Product Name: "+pro.getProduct_name()+" Product Price: "+pro.getPrice()+" Description: "+pro.getProd_description());
		}
		return list;
	}

}
