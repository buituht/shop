package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.daos.ProductDao;
import vn.iotstar.daos.impl.ProductDaoImpl;
import vn.iotstar.models.ProductModel;
import vn.iotstar.services.ProductService;


public class ProductServiceImpl implements ProductService {

	    //
		ProductDao productDao = new ProductDaoImpl(); 

	    @Override
	    public void insert(ProductModel product) {
	        productDao.insert(product);
	    }

	    @Override
	    public void edit(ProductModel product) {
	        productDao.edit(product);
	    }

	    @Override
	    public void delete(int id) {
	        productDao.delete(id);
	    }

	    @Override
	    public ProductModel get(int id) {
	        return productDao.get(id);
	    }

	    @Override
	    public List<ProductModel> findAll() {
	        return productDao.findAll();
	    }
}