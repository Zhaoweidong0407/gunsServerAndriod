package cn.stylefeng.guns.modular.system.model.result;

import lombok.Data;
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
public class DetailsResult implements Serializable {

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

}
