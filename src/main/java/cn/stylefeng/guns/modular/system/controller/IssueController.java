package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.system.entity.Issue;
import cn.stylefeng.guns.modular.system.model.params.IssueParam;
import cn.stylefeng.guns.modular.system.service.IssueService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 控制器
 *
 * @author wdl
 * @Date 2022-09-06 15:24:36
 */
@Controller
@RequestMapping("/issue")
public class IssueController extends BaseController {

    private String PREFIX = "/modular/system/issue";

    @Autowired
    private IssueService issueService;

    /**
     * 跳转到主页面
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/issue.html";
    }

    /**
     * 新增页面
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/issue_add.html";
    }

    /**
     * 编辑页面
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/issue_edit.html";
    }

    /**
     * 新增接口
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(IssueParam issueParam) {
        issueParam.setStatus(false);
        issueParam.setDate(new Date());
        this.issueService.add(issueParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(IssueParam issueParam) {
        this.issueService.update(issueParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(IssueParam issueParam) {
        this.issueService.delete(issueParam);
        return ResponseData.success();
    }

    /**
     * 問題已解決
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/resolved")
    @ResponseBody
    public ResponseData resolved(IssueParam issueParam) {
        this.issueService.resolved(issueParam);
        return ResponseData.success();
    }
    @RequestMapping("/unsolved")
    @ResponseBody
    public ResponseData unsolved(IssueParam issueParam) {
        this.issueService.unsolved(issueParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(IssueParam issueParam) {
        Issue detail = this.issueService.getById(issueParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author wdl
     * @Date 2022-09-06
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(IssueParam issueParam) {
        return this.issueService.findPageBySpec(issueParam);
    }

}


