package vn.iotstar.services;

import java.util.List;
import vn.iotstar.models.ProductModel;

public interface ProductService {
    void insert(ProductModel product);
    void edit(ProductModel product);
    void delete(int id);
    ProductModel get(int id);
    List<ProductModel> findAll();
}