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
 * @author 
 * @since 2022-09-05
 */
@Data
public class AndroiduserResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String mobile;

    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 是否可用
     */
    private Boolean state;

}
