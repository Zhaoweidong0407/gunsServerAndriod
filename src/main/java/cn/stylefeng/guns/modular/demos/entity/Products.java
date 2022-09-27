package cn.stylefeng.guns.modular.demos.entity;

public class Products {
    private String id;
    private String sketch; //搜索图片上的描述
    private String picture; //搜索图片路径

    public Products(String id, String sketch, String picture) {
        this.id = id;
        this.sketch = sketch;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
