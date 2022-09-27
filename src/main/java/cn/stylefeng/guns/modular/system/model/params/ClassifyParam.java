package cn.stylefeng.guns.modular.system.model.params;

import cn.stylefeng.guns.modular.system.entity.Classify;
import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Data
public class ClassifyParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 图标文字
     */
    private String title;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 图标路径
     */
    private String cover;

    /**
     * 几级目录
     */
    private String level;
    /**
     * 该分类首页产品标识
     */
    private String indexId;

    @Override
    public String checkParam() {
        return null;
    }

}
