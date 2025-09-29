package vn.iotstar.models;

public class CategoryModel {
    
    private int catId;     // Khớp với cột 'catId'
    private String cateName; // Khớp với cột 'CateName'
    private String image;    // Khớp với cột 'Image'
    private int status;    // Khớp với cột 'status'
    
    // Getters and Setters
    public int getCatId() {
        return catId;
    }
    public void setCatId(int catId) {
        this.catId = catId;
    }
    
    public String getCateName() {
        return cateName;
    }
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}