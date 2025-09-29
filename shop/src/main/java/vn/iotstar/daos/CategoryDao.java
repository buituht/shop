package vn.iotstar.daos;

import java.util.List;
import vn.iotstar.models.CategoryModel;

public interface CategoryDao {
    
    List<CategoryModel> findAll();
    
    CategoryModel get(int catId);
    
    void insert(CategoryModel category);
    
    void edit(CategoryModel category);
    
    void delete(int catId);
    
    CategoryModel findByCategoryName(String cateName);
}