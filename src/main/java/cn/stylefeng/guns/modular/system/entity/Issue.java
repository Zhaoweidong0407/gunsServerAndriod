package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdl
 * @since 2022-09-06
 */
@TableName("issue")
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电话号码
     */
    @TableField("phoneNumber")
    private String phoneNumber;

    /**
     * 问题、反馈和意见
     */
    @TableField("issueOpinion")
    private String issueOpinion;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 提交时间
     */
    @TableField("date")
    private Date date;

    /**
     * 是否已解決
     */
    @TableField("status")
    private boolean status;


    /**
     * 项目名称
     */
    @TableField("proName")
    private String proName;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIssueOpinion() {
        return issueOpinion;
    }

    public void setIssueOpinion(String issueOpinion) {
        this.issueOpinion = issueOpinion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Issue{" +
        "id=" + id +
        ", phoneNumber=" + phoneNumber +
        ", issueOpinion=" + issueOpinion +
        ", type=" + type +
        ", name=" + name +
        ", unit=" + unit +
        ", date=" + date +
        "}";
    }
}
