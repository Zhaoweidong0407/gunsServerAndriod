package cn.stylefeng.guns.modular.system.controller;


import cn.stylefeng.guns.GunsApplication;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.aop.NonStaticResourceHttpRequestHandler;
import cn.stylefeng.guns.modular.system.entity.Androiduser;
import cn.stylefeng.guns.modular.system.entity.Books;
import cn.stylefeng.guns.modular.system.entity.Details;
import cn.stylefeng.guns.modular.system.entity.Products;
import cn.stylefeng.guns.modular.system.model.params.AndroiduserParam;
import cn.stylefeng.guns.modular.system.model.params.IssueParam;
import cn.stylefeng.guns.modular.system.service.*;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.druid.util.StringUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/android")
public class AndroidControler {
    private final static Logger logger = LoggerFactory.getLogger(GunsApplication.class);
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Autowired
    private ProductsService productsService;
    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private DetailsService detailsService;
    @Autowired
    private BooksService booksService;
    @Autowired
    private GunsProperties gunsProperties;
    @Autowired
    private AndroiduserService androiduserService;
    @Autowired
    private IssueService issueService;

    @CrossOrigin("*")
    @RequestMapping("getIndex")
    public ResponseData getIndex() {
        Products products = new Products();
        //true：首页显示设备  false：首页不显示设备
        products.setIsIndex(true);
        List<Products> productList = productsService.queryByCndition(products);
        productList.forEach(product ->{
            product.setPicturePath(gunsProperties.getFileResouureStaticMap() + product.getPicturePath());
        });
        Map map = new HashMap();
        map.put("product_new",productList);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("search")
    public ResponseData search(String keyword) {
        Products products = new Products();
        products.setKeyWord(keyword);
        List<Products> productList = productsService.queryByCndition(products);
        productList.forEach(product ->{
            product.setPicturePath(gunsProperties.getFileResouureStaticMap() + product.getPicturePath());
        });
        Map map = new HashMap();
        map.put("list",productList);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("getClassify")
    public ResponseData getClassify(String keyword) {
        List list = classifyService.getClassify();
        Map map = new HashMap();
        map.put("list",list);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("getDetail")
    public ResponseData getDetail(String productId) {
//        Details param = new Details();
//        param.setProductId(productId);
//        Details details = detailsService.queryByCndition(param).get(0);
//        details.setPicPath(gunsProperties.getFileResouureStaticMap() + details.getPicPath());
//        Map map = new HashMap();
//        map.put("data",details);
//        return ResponseData.success(map);
        Details param = new Details();
        param.setProductId(productId);
        List<Details> details = detailsService.queryByCndition(param);
        details.forEach(detail -> {
            if(ToolUtil.isNotEmpty(detail.getPicPath())){
                detail.setPicPath(gunsProperties.getFileResouureStaticMap() + detail.getPicPath());
            }

        });
//        details.setPicPath(gunsProperties.getFileResouureStaticMap() + details.getPicPath());
        Map map = new HashMap();
        map.put("list",details);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("getBook")
    public ResponseData getBook(String productId) {
        Books books = booksService.getBookByProductId(productId);
        Map map = new HashMap();
        map.put("obj",books);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @GetMapping("/video")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //假如我把视频1.mp4放在了static下的video文件夹里面
        //sourcePath 是获取resources文件夹的绝对地址
        //realPath 即是视频所在的磁盘地址
        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
        String a = request.getParameter("id");
        String realPath = gunsProperties.getFileUploadPath()+a;
        Path filePath = Paths.get(realPath );
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(nonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

    @CrossOrigin("*")
    @RequestMapping("listbyclass")
    public ResponseData listbyclass(String classId) {
        cn.stylefeng.guns.modular.system.entity.Products products = new cn.stylefeng.guns.modular.system.entity.Products();
        products.setClassifyId(classId);
        List<Products> productList = productsService.queryByCndition(products);
        productList.forEach(product ->{
            product.setPicturePath(gunsProperties.getFileResouureStaticMap() + product.getPicturePath());
        });
        Map map = new HashMap();
        map.put("list",productList);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("register")
    public ResponseData register(String mobile,String password,String nickname,String idcode) {
        AndroiduserParam androiduserParam = new AndroiduserParam();
        androiduserParam.setMobile(mobile);
        androiduserParam.setPassword(password);
        androiduserParam.setNickname(nickname);
        androiduserParam.setIdcode(idcode);
        try {
            if (androiduserService.getCountByMobile(mobile) > 0){
                return ResponseData.error("注册失败,该电话已被注册！");
            }else {
                androiduserService.add(androiduserParam);
                return ResponseData.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("注册失败");
        }
    }

    @CrossOrigin("*")
    @RequestMapping("login")
    public ResponseData login(String mobile,String password) {
        try {
            Androiduser user = androiduserService.getUserByMobile(mobile,password);
            if (user != null && user.getState()){
                return ResponseData.success(user);
            }else if (user != null && !user.getState()){
                return ResponseData.error("该账号未审批，请联系管理员进行审批！");
            }else{
                return ResponseData.error("账号或者密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error("登录失败");
        }
    }

    @CrossOrigin("*")
    @RequestMapping("/addIssue")
    @ResponseBody
    public ResponseData addIssue(IssueParam issueParam) {
        issueParam.setStatus(false);
        issueParam.setDate(new Date());
        this.issueService.add(issueParam);
        return ResponseData.success();
    }
}
