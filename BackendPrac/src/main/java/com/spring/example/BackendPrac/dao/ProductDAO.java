package com.spring.example.BackendPrac.dao;

import com.spring.example.BackendPrac.entity.Product;

public interface ProductDAO {
public abstract Product getProductById(int id);
public abstract void saveProduct(Product product);

}
