package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 设备信息表 Mapper 接口
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface ProductsMapper extends BaseMapper<Products> {
    /**
     * 条件查询
     *
     * @param products 实例对象
     * @return 查询结果
     */
    List<Products> queryByCndition(Products products);

}
