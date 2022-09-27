package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Products;
import cn.stylefeng.guns.modular.system.model.params.ProductsParam;
import cn.stylefeng.guns.modular.system.model.result.ProductsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备信息表 服务类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface ProductsService extends IService<Products> {

    /**
     * 新增
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void add(ProductsParam param);

    /**
     * 删除
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void delete(ProductsParam param);

    /**
     * 更新
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void update(ProductsParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    ProductsResult findBySpec(ProductsParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    List<ProductsResult> findListBySpec(ProductsParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
     LayuiPageInfo findPageBySpec(ProductsParam param);

    /**
     * 条件查询
     *
     * @param products 实例对象
     * @return 查询结果
     */
     List<Products> queryByCndition(Products products);

}
