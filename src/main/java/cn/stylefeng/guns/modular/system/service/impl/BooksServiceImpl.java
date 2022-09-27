package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.PkeyGenerator;
import cn.stylefeng.guns.modular.system.entity.Books;
import cn.stylefeng.guns.modular.system.mapper.BooksMapper;
import cn.stylefeng.guns.modular.system.model.params.BooksParam;
import cn.stylefeng.guns.modular.system.model.result.BooksResult;
import  cn.stylefeng.guns.modular.system.service.BooksService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 使用手册表 服务实现类
 * </p>
 *
 * @author ZhaoWD
 * @since 2022-08-18
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {

    @Override
    public void add(BooksParam param){
        param.setId(PkeyGenerator.getUniqueString("BK"));
        Books entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BooksParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BooksParam param){
        Books oldEntity = getOldEntity(param);
        Books newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BooksResult findBySpec(BooksParam param){
        return null;
    }

    @Override
    public List<BooksResult> findListBySpec(BooksParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(BooksParam param){
        Page pageContext = getPageContext();
        LambdaQueryWrapper<Books> objectQueryWrapper = new LambdaQueryWrapper<>();
        objectQueryWrapper.eq(Books::getProductId,param.getProductId())
                .orderByDesc(Books::getFileType).orderByAsc(Books::getSort);
        IPage page = this.page(pageContext, objectQueryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BooksParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Books getOldEntity(BooksParam param) {
        return this.getById(getKey(param));
    }

    private Books getEntity(BooksParam param) {
        Books entity = new Books();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }
    @Autowired
    private GunsProperties gunsProperties;
    @Override
    public Books getBookByProductId(String productId) {
        Books books = new Books();
        books.setProductId(productId);
        //获取图片路径
        books.setFileType("P");
        List<Books> booksPicList = this.baseMapper.queryByCndition(books);
        List<String> bookPicPathList = new ArrayList<>();
        booksPicList.forEach(book -> {
            bookPicPathList.add(gunsProperties.getFileResouureStaticMap() + book.getFilePath());
        });
        //获取视频路径
        books.setFileType("M");
        List<Books> booksMoviePicList = this.baseMapper.queryByCndition(books);
        List<String> bookMoviePathList = new ArrayList<>();
        booksMoviePicList.forEach(book -> {
            bookMoviePathList.add(book.getFilePath());
        });
        books.setPicPaths(bookPicPathList);
        books.setMoviePaths(bookMoviePathList);
        return books;
    }
}
