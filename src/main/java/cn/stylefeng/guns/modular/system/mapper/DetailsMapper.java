package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Details;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品详情表 Mapper 接口
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface DetailsMapper extends BaseMapper<Details> {
    List<Details> queryByCondition(Details details);
}
