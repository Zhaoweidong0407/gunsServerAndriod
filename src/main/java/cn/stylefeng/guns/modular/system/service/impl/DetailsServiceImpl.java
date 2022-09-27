package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.PkeyGenerator;
import cn.stylefeng.guns.modular.system.entity.Books;
import cn.stylefeng.guns.modular.system.entity.Details;
import cn.stylefeng.guns.modular.system.mapper.DetailsMapper;
import cn.stylefeng.guns.modular.system.model.params.DetailsParam;
import cn.stylefeng.guns.modular.system.model.result.DetailsResult;
import  cn.stylefeng.guns.modular.system.service.DetailsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 产品详情表 服务实现类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Service
public class DetailsServiceImpl extends ServiceImpl<DetailsMapper, Details> implements DetailsService {

    @Override
    public void add(DetailsParam param){
        param.setId(PkeyGenerator.getUniqueString("DK"));
        Details entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(DetailsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(DetailsParam param){
        Details oldEntity = getOldEntity(param);
        Details newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public DetailsResult findBySpec(DetailsParam param){
        return null;
    }

    @Override
    public List<DetailsResult> findListBySpec(DetailsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(DetailsParam param){
        Page pageContext = getPageContext();
        LambdaQueryWrapper<Details> objectQueryWrapper = new LambdaQueryWrapper<>();
        objectQueryWrapper.eq(Details::getProductId,param.getProductId());
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(DetailsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Details getOldEntity(DetailsParam param) {
        return this.getById(getKey(param));
    }

    private Details getEntity(DetailsParam param) {
        Details entity = new Details();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }


    @Override
    public List<Details> queryByCndition(Details details) {
        return this.baseMapper.queryByCondition(details);
    }

}
