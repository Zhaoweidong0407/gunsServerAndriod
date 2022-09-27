package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 使用手册表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@TableName("books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 产品详情表ID
     */
    @TableField("detailId")
    private String detailId;

    /**
     * 产品表ID
     */
    @TableField("productId")
    private String productId;

    /**
     * 图片顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 图片路径
     */
    @TableField("filePath")
    private String filePath;

    /**
     * 文件类型
     */
    @TableField("fileType")
    private String fileType;

    @TableField(exist = false)
    private List<String> picPaths;

    @TableField(exist = false)
    private List<String> moviePaths;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    @Override
    public String toString() {
        return "Books{" +
                "id='" + id + '\'' +
                ", detailId='" + detailId + '\'' +
                ", productId='" + productId + '\'' +
                ", sort=" + sort +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", picPaths=" + picPaths +
                ", moviePaths=" + moviePaths +
                '}';
    }
}
