package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Issue;
import cn.stylefeng.guns.modular.system.mapper.IssueMapper;
import cn.stylefeng.guns.modular.system.model.params.IssueParam;
import cn.stylefeng.guns.modular.system.model.result.IssueResult;
import  cn.stylefeng.guns.modular.system.service.IssueService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdl
 * @since 2022-09-06
 */
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {

    @Override
    public void add(IssueParam param){
        Issue entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(IssueParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void resolved(IssueParam param){
        param.setStatus(true);
        this.update(param);
    }
    @Override
    public void unsolved(IssueParam param){
        param.setStatus(false);
        this.update(param);
    }

    @Override
    public void update(IssueParam param){
        Issue oldEntity = getOldEntity(param);
        Issue newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public IssueResult findBySpec(IssueParam param){
        return null;
    }

    @Override
    public List<IssueResult> findListBySpec(IssueParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(IssueParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Issue> objectQueryWrapper = new QueryWrapper<>();

        if(StringUtils.isNotEmpty(param.getPhoneNumber())){
            objectQueryWrapper.like("phoneNumber",param.getPhoneNumber());
        }

        if(StringUtils.isNotEmpty(param.getName())){
            objectQueryWrapper.like("name",param.getName());
        }

        if(StringUtils.isNotEmpty(param.getUnit())){
            objectQueryWrapper.like("unit",param.getUnit());
        }

        if(StringUtils.isNotEmpty(param.getIssueOpinion())){
            objectQueryWrapper.like("issueOpinion",param.getIssueOpinion());
        }

        if(StringUtils.isNotEmpty(param.getProName())){
            objectQueryWrapper.like("proName", param.getProName());
        }

        if(StringUtils.isNotEmpty(param.getType())){
            objectQueryWrapper.eq("type", param.getType());
        }

        if(StringUtils.isNotEmpty(param.getStatusBak())){
            objectQueryWrapper.eq("status", param.getStatusBak());
        }

        if(StringUtils.isNotEmpty(param.getDateScope())){
            objectQueryWrapper.between("date",param.getDateScope().split(" - ")[0]+"-01 00:00:00",param.getDateScope().split(" - ")[1]+"-31 00:00:00");
        }

        objectQueryWrapper.orderByDesc("date");
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(IssueParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Issue getOldEntity(IssueParam param) {
        return this.getById(getKey(param));
    }

    private Issue getEntity(IssueParam param) {
        Issue entity = new Issue();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
