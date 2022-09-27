package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Details;
import cn.stylefeng.guns.modular.system.model.params.DetailsParam;
import cn.stylefeng.guns.modular.system.model.result.DetailsResult;
import cn.stylefeng.guns.modular.system.service.DetailsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 产品详情表控制器
 *
 * @author ZhaoWD
 * @Date 2022-08-18 12:50:55
 */
@Controller
@RequestMapping("/details")
public class DetailsController extends BaseController {

    private String PREFIX = "/modular/system/details";

    @Autowired
    private DetailsService detailsService;

    /**
     * 跳转到主页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/details.html";
    }

    /**
     * 新增页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/details_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/details_edit.html";
    }

    /**
     * 编辑页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/editByProduct")
    public String editByProduct() {
        return PREFIX + "/details_edit_products.html";
    }

    /**
     * 编辑页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addByProduct")
    public String addByProduct() {
        return PREFIX + "/details_add_products.html";
    }

    /**
     * 新增接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(DetailsParam detailsParam) {
        this.detailsService.add(detailsParam);
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
    public ResponseData editItem(DetailsParam detailsParam) {
        this.detailsService.update(detailsParam);
//        Details details = new Details();
//
//        details.setProductId(detailsParam.getProductId());
//        List<Details> list = this.detailsService.queryByCndition(details);
//        if (list.size() > 0){
//            detailsParam.setId(list.get(0).getId());
//            this.detailsService.update(detailsParam);
//        }else {
//            this.detailsService.add(detailsParam);
//        }
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
    public ResponseData delete(DetailsParam detailsParam) {
        this.detailsService.delete(detailsParam);
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
    public ResponseData detail(DetailsParam detailsParam) {
        Details detail = this.detailsService.getById(detailsParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查看详情接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/detailByProduct")
    @ResponseBody
    public ResponseData detailByProduct(DetailsParam detailsParam) {
        Details details = new Details();
        details.setProductId(detailsParam.getProductId());
        List<Details> list = this.detailsService.queryByCndition(details);
        if (list.size() > 0){
            details = list.get(0);
        }
        return ResponseData.success(details);
    }

    /**
     * 查询列表
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(DetailsParam detailsParam) {
        return this.detailsService.findPageBySpec(detailsParam);
    }

}


