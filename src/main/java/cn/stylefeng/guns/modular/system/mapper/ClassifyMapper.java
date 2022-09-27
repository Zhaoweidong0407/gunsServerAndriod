package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Classify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface ClassifyMapper extends BaseMapper<Classify> {

    List<Classify> queryByCndition(Classify classify);

    List<Classify> queryByPId(String pId);
}
