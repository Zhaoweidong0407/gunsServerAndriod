package cn.stylefeng.guns.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 产品详情表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Data
public class DetailsParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 产品表ID
     */
    private String productId;

    /**
     * 内容图片地址
     */
    private String picPath;

    /**
     * 详情页标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 说明书ID
     */
    private String bookId;

    @Override
    public String checkParam() {
        return null;
    }

}
