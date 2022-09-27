package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Androiduser;
import cn.stylefeng.guns.modular.system.model.params.AndroiduserParam;
import cn.stylefeng.guns.modular.system.service.AndroiduserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author 
 * @Date 2022-09-05 16:20:14
 */
@Controller
@RequestMapping("/androiduser")
public class AndroiduserController extends BaseController {

    private String PREFIX = "/modular/system/androiduser";

    @Autowired
    private AndroiduserService androiduserService;

    /**
     * 跳转到主页面
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/androiduser.html";
    }

    /**
     * 新增页面
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/androiduser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/androiduser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(AndroiduserParam androiduserParam) {
        this.androiduserService.add(androiduserParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(AndroiduserParam androiduserParam) {
        this.androiduserService.update(androiduserParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(AndroiduserParam androiduserParam) {
        this.androiduserService.delete(androiduserParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author 
     * @Date 2022-09-05
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(AndroiduserParam androiduserParam) {
        Androiduser detail = this.androiduserService.getById(androiduserParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author 
     * @Date 2022-09-05
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(AndroiduserParam androiduserParam) {
        return this.androiduserService.findPageBySpec(androiduserParam);
    }

    /**
     * 设置首页显示
     */
    @RequestMapping("/setStatus")
    @ResponseBody
    public ResponseData setStatus(@RequestParam("id") String id, @RequestParam("state") boolean state) {
        if (ToolUtil.isEmpty(id) || ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        try {
            Androiduser androiduser = androiduserService.getById(id);
            androiduser.setState(state);
            androiduserService.updateById(androiduser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS_TIP;
    }

}


