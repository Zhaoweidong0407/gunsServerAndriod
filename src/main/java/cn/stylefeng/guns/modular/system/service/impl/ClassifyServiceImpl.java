package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.PkeyGenerator;
import cn.stylefeng.guns.modular.system.entity.Classify;
import cn.stylefeng.guns.modular.system.mapper.ClassifyMapper;
import cn.stylefeng.guns.modular.system.model.params.ClassifyParam;
import cn.stylefeng.guns.modular.system.model.result.ClassifyResult;
import cn.stylefeng.guns.modular.system.entity.Products;
import  cn.stylefeng.guns.modular.system.service.ClassifyService;
import cn.stylefeng.guns.modular.system.service.ProductsService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {

    @Override
    public void add(ClassifyParam param){
        param.setId(PkeyGenerator.getUniqueString("CK"));
        Classify entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ClassifyParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ClassifyParam param){
        Classify oldEntity = getOldEntity(param);
        Classify newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ClassifyResult findBySpec(ClassifyParam param){
        return null;
    }

    @Override
    public List<ClassifyResult> findListBySpec(ClassifyParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ClassifyParam param){
        Page pageContext = getPageContext();
        QueryWrapper<Classify> objectQueryWrapper = new QueryWrapper<>();
        if (ToolUtil.isNotEmpty(param.getTitle())){
            objectQueryWrapper.like("title",param.getTitle());
        }
        objectQueryWrapper.orderByAsc("level");
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ClassifyParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Classify getOldEntity(ClassifyParam param) {
        return this.getById(getKey(param));
    }

    private Classify getEntity(ClassifyParam param) {
        Classify entity = new Classify();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public List<Classify> queryByCndition(Classify classify) {
        return this.baseMapper.queryByCndition(classify);
    }

    @Autowired
    private ProductsService productsService;
    @Autowired
    private GunsProperties gunsProperties;
    @Override
    public List<Classify> getClassify() {
        Classify classify = new Classify();
        classify.setLevel("1");
        //获取一级菜单列表
        List<Classify> classifyList = this.queryByCndition(classify);
        classifyList.forEach(classify1 -> {
            List<Classify> classifyArrayList = new ArrayList<>();
            //获取二级菜单列表
            List<Classify> classifyList2 = this.baseMapper.queryByPId(classify1.getId());
            classifyList2.forEach(classify2 -> {
                Products products = new Products();
                products.setClassifyId(classify2.getId());
                //根据二级ID去产品表查询产品
                List<Products> list = productsService.queryByCndition(products);
                List<Classify> ProductList = new ArrayList<>();
                list.forEach(product ->{
                    //该二级分类下不显示设为首页的产品
                    if (ToolUtil.isEmpty(classify2.getIndexId())){
                        Classify classify3 = new Classify();
                        classify3.setId(product.getId());
                        classify3.setLevel("3");
                        classify3.setPid(product.getClassifyId());
                        classify3.setTitle(product.getSketch());
                        classify3.setCover(gunsProperties.getFileResouureStaticMap()+product.getPicturePath());
                        ProductList.add(classify3);
                    }else {
                        if (!classify2.getIndexId().equals(product.getId())){
                            Classify classify3 = new Classify();
                            classify3.setId(product.getId());
                            classify3.setLevel("3");
                            classify3.setPid(product.getClassifyId());
                            classify3.setTitle(product.getSketch());
                            classify3.setCover(gunsProperties.getFileResouureStaticMap()+product.getPicturePath());
                            ProductList.add(classify3);
                        }
                    }
                });
                //将产品放入二级列表下
                classify2.setChild(ProductList);
                classifyArrayList.add(classify2);
            });
            classify1.setChild(classifyArrayList);
        });
        return classifyList;
    }

}
