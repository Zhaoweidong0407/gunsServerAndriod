package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.core.util.FIleNameUtil;
import cn.stylefeng.guns.core.util.StringArrayUtil;
import cn.stylefeng.guns.modular.system.entity.Books;
import cn.stylefeng.guns.modular.system.model.params.BooksParam;
import cn.stylefeng.guns.modular.system.service.BooksService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * 使用手册表控制器
 *
 * @author ZhaoWD
 * @Date 2022-08-18 12:50:55
 */
@Controller
@RequestMapping("/books")
public class BooksController extends BaseController {

    private String PREFIX = "/modular/system/books";

    @Autowired
    private BooksService booksService;
    @Autowired
    private GunsProperties gunsProperties;

    /**
     * 跳转到主页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/books.html";
    }

    /**
     * 新增页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addPic")
    public String add() {
        return PREFIX + "/books_add.html";
    }

    @RequestMapping("/addMov")
    public String addMov() {
        return PREFIX + "/books_add_mov.html";
    }

    /**
     * 编辑页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/books_edit.html";
    }

    /**
     * 新增图片接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(BooksParam booksParam) {
        String[] filePathArrs = StringArrayUtil.toStringArray(booksParam.getFilePath());
        List<String> filePaths= Arrays.asList(filePathArrs);
        for (int i = 0;i<filePaths.size();i++){
            if (ToolUtil.isNotEmpty(filePaths.get(i))){
                booksParam.setSort(i + 1);
                booksParam.setFilePath(filePaths.get(i).replace("\"",""));
                this.booksService.add(booksParam);
            }
        }
        return ResponseData.success();
    }

    /**
     * 新增视频接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addMovItem")
    @ResponseBody
    public ResponseData addMovItem(BooksParam booksParam,@RequestParam(value = "file") MultipartFile movie) {
        String movieName = UUID.randomUUID().toString() + "--" + FIleNameUtil.getFileSuffix(movie.getOriginalFilename()) + "." + ToolUtil.getFileSuffix(movie.getOriginalFilename());
        booksParam.setFilePath(movieName);
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            movie.transferTo(new File(fileSavePath + movieName));
            this.booksService.add(booksParam);
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(BooksParam booksParam) {
        this.booksService.update(booksParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(BooksParam booksParam) {
        this.booksService.delete(booksParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(BooksParam booksParam) {
        Books detail = this.booksService.getById(booksParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(BooksParam booksParam) {
        return this.booksService.findPageBySpec(booksParam);
    }

}


