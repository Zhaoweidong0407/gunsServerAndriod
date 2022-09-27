package cn.stylefeng.guns.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class IssueParam implements Serializable, BaseValidatingParam {

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
    private String dateScope;

    /**
     * 是否已解決
     */
    private boolean status;
    private String statusBak;


    /**
     * 项目名称
     */
    private String proName;
    @Override
    public String checkParam() {
        return null;
    }

}
