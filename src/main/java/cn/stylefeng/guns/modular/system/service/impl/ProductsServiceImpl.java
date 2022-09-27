package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.PkeyGenerator;
import cn.stylefeng.guns.modular.system.entity.Products;
import cn.stylefeng.guns.modular.system.mapper.ProductsMapper;
import cn.stylefeng.guns.modular.system.model.params.ProductsParam;
import cn.stylefeng.guns.modular.system.model.result.ProductsResult;
import  cn.stylefeng.guns.modular.system.service.ProductsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备信息表 服务实现类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

    @Override
    public void add(ProductsParam param){
        param.setId(PkeyGenerator.getUniqueString("PK"));
        Products entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ProductsParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ProductsParam param){
        Products oldEntity = getOldEntity(param);
        Products newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ProductsResult findBySpec(ProductsParam param){
        return null;
    }

    @Override
    public List<ProductsResult> findListBySpec(ProductsParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ProductsParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Products> objectQueryWrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(param.getKeyWord())){
            objectQueryWrapper.like("keyWord",param.getKeyWord());
        }
        if (ToolUtil.isNotEmpty(param.getSketch())){
            objectQueryWrapper.like("sketch",param.getSketch());
        }
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ProductsParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Products getOldEntity(ProductsParam param) {
        return this.getById(getKey(param));
    }

    private Products getEntity(ProductsParam param) {
        Products entity = new Products();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public List<Products> queryByCndition(Products products) {
        return this.baseMapper.queryByCndition(products);
    }

}
