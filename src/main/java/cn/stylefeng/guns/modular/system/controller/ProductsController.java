package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.UserDict;
import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Products;
import cn.stylefeng.guns.modular.system.model.params.ProductsParam;
import cn.stylefeng.guns.modular.system.service.ProductsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 设备信息表控制器
 *
 * @author ZhaoWD
 * @Date 2022-08-18 12:50:55
 */
@Controller
@RequestMapping("/products")
public class ProductsController extends BaseController {

    private String PREFIX = "/modular/system/products/";

    @Autowired
    private ProductsService productsService;

    /**
     * 跳转到主页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "products.html";
    }

    /**
     * 新增页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "products_add.html";
    }

    /**
     * 新增页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/order")
    public String order() {
        return "/modular/system/fileupload/order_edit.html";
    }

    /**
     * 编辑页面
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "products_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ZhaoWD
     * @Date 2022-08-18
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(ProductsParam productsParam) {
        this.productsService.add(productsParam);
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
    public ResponseData editItem(ProductsParam productsParam) {
        this.productsService.update(productsParam);
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
    public ResponseData delete(ProductsParam productsParam) {
        this.productsService.delete(productsParam);
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
    public ResponseData detail(ProductsParam productsParam) {
        Products detail = this.productsService.getById(productsParam.getId());
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
    public LayuiPageInfo list(ProductsParam productsParam) {
        LayuiPageInfo layuiPageInfo = this.productsService.findPageBySpec(productsParam);
        return this.productsService.findPageBySpec(productsParam);
    }

    /**
     * 设置首页显示
     */
    @RequestMapping("/setIsIndex")
    @ResponseBody
    public ResponseData unfreeze(@RequestParam("productId") String productId,@RequestParam("isIndex") boolean isIndex) {
        if (ToolUtil.isEmpty(productId) || ToolUtil.isEmpty(isIndex)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Products param = new Products();
        param.setId(productId);
        Products products = this.productsService.queryByCndition(param).get(0);
        products.setIsIndex(isIndex);
        this.productsService.updateById(products);
        return SUCCESS_TIP;
    }

}


