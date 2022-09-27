package cn.stylefeng.guns.modular.demos.entity;

import java.util.List;

public class BooksPic {
    private String id;
    private String Pid;
    private int sort;
    private List<String> picPaths;
    private List<String> moviePaths;

    public BooksPic() {
    }

    public BooksPic(String id, String pid, int sort, List<String> picPaths, List<String> moviePaths) {
        this.id = id;
        Pid = pid;
        this.sort = sort;
        this.picPaths = picPaths;
        this.moviePaths = moviePaths;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<String> getPicPaths() {
        return picPaths;
    }

    public void setPicPaths(List<String> picPaths) {
        this.picPaths = picPaths;
    }

    public List<String> getMoviePaths() {
        return moviePaths;
    }

    public void setMoviePaths(List<String> moviePaths) {
        this.moviePaths = moviePaths;
    }
}
