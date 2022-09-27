package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 使用手册表 Mapper 接口
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface BooksMapper extends BaseMapper<Books> {
    List<Books> queryByCndition(Books books);
}
