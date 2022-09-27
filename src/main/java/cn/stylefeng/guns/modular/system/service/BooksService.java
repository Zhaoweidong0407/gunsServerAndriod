package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Books;
import cn.stylefeng.guns.modular.system.model.params.BooksParam;
import cn.stylefeng.guns.modular.system.model.result.BooksResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 使用手册表 服务类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
public interface BooksService extends IService<Books> {

    /**
     * 新增
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void add(BooksParam param);

    /**
     * 删除
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void delete(BooksParam param);

    /**
     * 更新
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    void update(BooksParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    BooksResult findBySpec(BooksParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    List<BooksResult> findListBySpec(BooksParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
     LayuiPageInfo findPageBySpec(BooksParam param);

     //安卓端请求接口
     Books getBookByProductId(String productId);

}
