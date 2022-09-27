package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Classify;
import cn.stylefeng.guns.modular.system.model.params.ClassifyParam;
import cn.stylefeng.guns.modular.system.service.ClassifyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 分类表控制器
 *
 * @author ZhaoWD
 * @Date 2022-08-18 12:50:55
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController extends BaseController {

    private String PREFIX = "/modular/system/classify";

    @Autowired
    private ClassifyService classifyService;

    /**
     * 跳转到主页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/classify.html";
    }

    /**
     * 新增页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/classify_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/classify_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ClassifyParam classifyParam) {
        this.classifyService.add(classifyParam);
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
    public ResponseData editItem(ClassifyParam classifyParam) {
        this.classifyService.update(classifyParam);
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
    public ResponseData delete(ClassifyParam classifyParam) {
        this.classifyService.delete(classifyParam);
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
    public ResponseData detail(ClassifyParam classifyParam) {
        Classify detail = this.classifyService.getById(classifyParam.getId());
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
    public LayuiPageInfo list(ClassifyParam classifyParam) {
        return this.classifyService.findPageBySpec(classifyParam);
    }

}


