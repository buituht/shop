package vn.iotstar.services;
import java.util.List;
import vn.iotstar.models.CategoryModel;
public interface CategoryService {
	

	CategoryModel findByUserName(String category_name);

	CategoryModel findByCategoryName(String cate_name);

	void insertCategory(CategoryModel category);

	List<CategoryModel> getAll();

	CategoryModel get(int id);

	void edit(CategoryModel category);
	  
	// Thêm phương thức insert để có thể gọi từ Controller
	void insert(CategoryModel category);
	void delete(int id);
	List<CategoryModel> findAll(); 
	    	

	
	


	
}


