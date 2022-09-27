package cn.stylefeng.guns.modular.demos.entity;

import java.util.List;

public class Classify {
    private String id;
    private String title;
    private String pid;
    private String cover;
    private String level;
    private List<Classify> child;

    public Classify() {
    }

    public Classify(String id, String title, String pid, String cover, String level, List<Classify> child) {
        this.id = id;
        this.title = title;
        this.pid = pid;
        this.cover = cover;
        this.level = level;
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Classify> getChild() {
        return child;
    }

    public void setChild(List<Classify> child) {
        this.child = child;
    }
}
