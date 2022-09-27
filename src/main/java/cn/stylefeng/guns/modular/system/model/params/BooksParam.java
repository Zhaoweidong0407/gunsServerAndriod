package cn.stylefeng.guns.modular.system.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 使用手册表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Data
public class BooksParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 产品详情表ID
     */
    private String detailId;

    /**
     * 产品表ID
     */
    private String productId;

    /**
     * 图片顺序
     */
    private Integer sort;

    /**
     * 图片路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    @Override
    public String checkParam() {
        return null;
    }

}
