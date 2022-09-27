package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 产品详情表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@TableName("details")
public class Details implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 产品表ID
     */
    @TableField("productId")
    private String productId;

    /**
     * 内容图片地址
     */
    @TableField("picPath")
    private String picPath;

    /**
     * 详情页标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 说明书ID
     */
    @TableField("bookId")
    private String bookId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Details{" +
        "id=" + id +
        ", productId=" + productId +
        ", picPath=" + picPath +
        ", title=" + title +
        ", content=" + content +
        ", bookId=" + bookId +
        "}";
    }
}
