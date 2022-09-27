package cn.stylefeng.guns.modular.demos.entity;

public class Details {
    private String id;
    private String pic;
    private String title;
    private String name;

    public Details() {
    }

    public Details(String id, String pic, String title, String name) {
        this.id = id;
        this.pic = pic;
        this.title = title;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
