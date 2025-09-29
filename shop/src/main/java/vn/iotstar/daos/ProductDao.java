package vn.iotstar.daos;

import java.util.List;
import vn.iotstar.models.ProductModel;

public interface ProductDao {
    void insert(ProductModel product);
    void edit(ProductModel product);
    void delete(int id);
    ProductModel get(int id);
    List<ProductModel> findAll();
}