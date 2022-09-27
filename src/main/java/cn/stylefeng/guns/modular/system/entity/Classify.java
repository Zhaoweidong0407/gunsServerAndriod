package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import net.bytebuddy.asm.Advice;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@TableName("classify")
public class Classify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 图标文字
     */
    @TableField("title")
    private String title;

    /**
     * 父ID
     */
    @TableField("pid")
    private String pid;

    /**
     * 图标路径
     */
    @TableField("cover")
    private String cover;

    /**
     * 几级目录
     */
    @TableField("level")
    private String level;

    /**
     * 该分类首页产品标识
     */
    @TableField("indexId")
    private String indexId;

    @TableField(exist = false)
    private List<Classify> child;



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

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public List<Classify> getChild() {
        return child;
    }

    public void setChild(List<Classify> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pid='" + pid + '\'' +
                ", cover='" + cover + '\'' +
                ", level='" + level + '\'' +
                ", child=" + child +
                '}';
    }
}
