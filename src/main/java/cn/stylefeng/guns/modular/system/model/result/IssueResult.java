package cn.stylefeng.guns.modular.system.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdl
 * @since 2022-09-06
 */
@Data
public class IssueResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 问题、反馈和意见
     */
    private String issueOpinion;

    /**
     * 类型
     */
    private String type;

    /**
     * 姓名
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 提交时间
     */
    private Date date;
    /**
     * 是否已解決
     */
    private boolean status;

    /**
     * 项目名称
     */
    private String proName;
}
