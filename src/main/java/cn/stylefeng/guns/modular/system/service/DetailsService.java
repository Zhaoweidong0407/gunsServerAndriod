package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Details;
import cn.stylefeng.guns.modular.system.model.params.DetailsParam;
import cn.stylefeng.guns.modular.system.model.result.DetailsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品详情表 服务类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface DetailsService extends IService<Details> {

    /**
     * 新增
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void add(DetailsParam param);

    /**
     * 删除
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void delete(DetailsParam param);

    /**
     * 更新
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void update(DetailsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    DetailsResult findBySpec(DetailsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    List<DetailsResult> findListBySpec(DetailsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
     LayuiPageInfo findPageBySpec(DetailsParam param);

     List<Details> queryByCndition(Details details);

}
