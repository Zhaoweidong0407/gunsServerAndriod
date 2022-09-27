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
 * @author 
 * @since 2022-09-05
 */
@Data
public class AndroiduserParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String mobile;

    private String password;

    /**
     * 军官证
     */
    private String idcode;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 是否可用
     */
    private Boolean state;

    @Override
    public String checkParam() {
        return null;
    }

}
