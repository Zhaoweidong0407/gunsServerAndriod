package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Androiduser;
import cn.stylefeng.guns.modular.system.model.params.AndroiduserParam;
import cn.stylefeng.guns.modular.system.model.result.AndroiduserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-09-05
 */
public interface AndroiduserService extends IService<Androiduser> {

    /**
     * 新增
     *
     * @author 
     * @Date 2022-09-05
     */
    void add(AndroiduserParam param);

    /**
     * 删除
     *
     * @author 
     * @Date 2022-09-05
     */
    void delete(AndroiduserParam param);

    /**
     * 更新
     *
     * @author 
     * @Date 2022-09-05
     */
    void update(AndroiduserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author 
     * @Date 2022-09-05
     */
    AndroiduserResult findBySpec(AndroiduserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author 
     * @Date 2022-09-05
     */
    List<AndroiduserResult> findListBySpec(AndroiduserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author 
     * @Date 2022-09-05
     */
     LayuiPageInfo findPageBySpec(AndroiduserParam param);

     int getCountByMobile(String mobile);

     Androiduser getUserByMobile(String mobile,String passWord);

}
