package niit.ecommerce.main.MainBackEnd.Dao;

import java.util.List;

import niit.ecommerce.main.MainBackEnd.dto.Category;
import niit.ecommerce.main.MainBackEnd.dto.Product;

public interface ProductDao {

	boolean addProduct(Product product);

	boolean updateProduct(Product product);

	boolean deleteProduct(Long id);
	
	public Product getProductByProductId(Long product_id);

	public List<Product> getProductList();
	
	public List<Product> getActiveProducts();
	
	public List<Product> getActiveProductByCategory(Category category);
	
	public List<Product> getProductByProductName(String search);
	
	public List<Product> select8Product();
	
	 
}
