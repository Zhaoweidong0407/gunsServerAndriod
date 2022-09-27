package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Classify;
import cn.stylefeng.guns.modular.system.model.params.ClassifyParam;
import cn.stylefeng.guns.modular.system.model.result.ClassifyResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface ClassifyService extends IService<Classify> {

    /**
     * 新增
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void add(ClassifyParam param);

    /**
     * 删除
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void delete(ClassifyParam param);

    /**
     * 更新
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void update(ClassifyParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    ClassifyResult findBySpec(ClassifyParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    List<ClassifyResult> findListBySpec(ClassifyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
     LayuiPageInfo findPageBySpec(ClassifyParam param);

     List<Classify> queryByCndition(Classify classify);

     List<Classify> getClassify();

}
