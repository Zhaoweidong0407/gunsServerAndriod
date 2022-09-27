package cn.stylefeng.guns.modular.system.model.params;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设备信息表
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Data
public class ProductsParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 图标描述
     */
    private String sketch;

    /**
     * 图标路径
     */
    private String picturePath;

    /**
     * 分类父ID
     */
    private String classifyId;

    /**
     * 是否在首页显示
     */
    private Boolean isIndex;

    /**
     * 搜索关键字
     */
    private String keyWord;

    @Override
    public String checkParam() {
        return null;
    }

}
