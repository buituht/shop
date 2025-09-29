package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.daos.CategoryDao;
import vn.iotstar.daos.impl.CategoryDaoImpl;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
    public List<CategoryModel> findAll() {
        return categoryDao.findAll(); 
    }
	
	@Override
	public CategoryModel get(int catId) {
		return categoryDao.get(catId);
	}

	@Override
	public CategoryModel findByCategoryName(String cate_name) {
		return categoryDao.findByCategoryName(cate_name);
	}

	@Override
	public void insert(CategoryModel category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(CategoryModel category) {
		categoryDao.edit(category);
	}

	@Override
	public void delete(int catId) {
		categoryDao.delete(catId);
	}

	@Override
	public CategoryModel findByUserName(String category_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCategory(CategoryModel category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}