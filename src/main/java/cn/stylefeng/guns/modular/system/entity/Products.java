package cn.stylefeng.guns.modular.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 设备信息表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@TableName("products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 图标描述
     */
    @TableField("sketch")
    private String sketch;

    /**
     * 图标路径
     */
    @TableField("picturePath")
    private String picturePath;

    /**
     * 分类父ID
     */
    @TableField("classifyId")
    private String classifyId;

    /**
     * 是否在首页显示
     */
    @TableField("isIndex")
    private Boolean isIndex;

    /**
     * 搜索关键字
     */
    @TableField("keyWord")
    private String keyWord;


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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public Boolean getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(Boolean isIndex) {
        this.isIndex = isIndex;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "Products{" +
        "id=" + id +
        ", sketch=" + sketch +
        ", picturePath=" + picturePath +
        ", classifyId=" + classifyId +
        ", isIndex=" + isIndex +
        ", keyWord=" + keyWord +
        "}";
    }
}
