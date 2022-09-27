package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Issue;
import cn.stylefeng.guns.modular.system.model.params.IssueParam;
import cn.stylefeng.guns.modular.system.model.result.IssueResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdl
 * @since 2022-09-06
 */
public interface IssueService extends IService<Issue> {

    /**
     * 新增
     *
     * @author wdl
     * @Date 2022-09-06
     */
    void add(IssueParam param);

    /**
     * 删除
     *
     * @author wdl
     * @Date 2022-09-06
     */
    void delete(IssueParam param);

    /**
     * 標識已解決
     *
     * @author wdl
     * @Date 2022-09-06
     */
    public void resolved(IssueParam param);
    public void unsolved(IssueParam param);
    /**
     * 更新
     *
     * @author wdl
     * @Date 2022-09-06
     */
    void update(IssueParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author wdl
     * @Date 2022-09-06
     */
    IssueResult findBySpec(IssueParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author wdl
     * @Date 2022-09-06
     */
    List<IssueResult> findListBySpec(IssueParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author wdl
     * @Date 2022-09-06
     */
     LayuiPageInfo findPageBySpec(IssueParam param);

}
