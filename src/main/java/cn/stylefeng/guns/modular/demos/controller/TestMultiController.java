/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.demos.controller;

import cn.stylefeng.guns.GunsApplication;
import cn.stylefeng.guns.modular.demos.entity.Classify;
import cn.stylefeng.guns.modular.demos.entity.Details;
import cn.stylefeng.guns.modular.demos.entity.Products;
import cn.stylefeng.guns.modular.demos.service.TestMultiDbService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试多数据源回滚
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/multi")
public class TestMultiController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(GunsApplication.class);

    @Autowired
    private TestMultiDbService testMultiDbService;

    @CrossOrigin("*")
    @RequestMapping("test")
    public ResponseData auth() {
        Products productNew1 = new Products("463","设备1","http://localhost:8088/gunsServer/static/captured/picture/pic1/a4.png");
        Products productNew2 = new Products("491","设备2","http://localhost:8088/gunsServer/static/captured/picture/pic1/6.png");
        List productNewList = new ArrayList();
        productNewList.add(productNew1);
        productNewList.add(productNew2);
        Map map = new HashMap();
        map.put("product_new",productNewList);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("test2")
    @ResponseBody
    public ResponseData test2(String keyword) {
        Products productNew1 = new Products("463","设备1","http://localhost:8088/gunsServer/static/captured/picture/pic1/a4.png");
        Products productNew2 = new Products("491","设备2","http://localhost:8088/gunsServer/static/captured/picture/pic1/6.png");
        List productNewList = new ArrayList();
        productNewList.add(productNew1);
        productNewList.add(productNew2);
        Map map = new HashMap();
        map.put("list",productNewList);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("test3")
    @ResponseBody
    public ResponseData test3(String keyword) {
        Classify classify1_1_1 = new Classify("7","7","4","http://localhost:8088/gunsServer/static/captured/picture/pic1/a4.png","3",new ArrayList<>());
        Classify classify1_1_2 = new Classify("8","8","4","http://localhost:8088/gunsServer/static/captured/picture/pic1/9.png","3",new ArrayList<>());
        Classify classify1_1_3 = new Classify("9","9","4","http://localhost:8088/gunsServer/static/captured/picture/pic1/a4.png","3",new ArrayList<>());
        List classify1_1_1s = new ArrayList();
        classify1_1_1s.add(classify1_1_1);
        classify1_1_1s.add(classify1_1_2);
        classify1_1_1s.add(classify1_1_3);

        Classify classify1_2_1 = new Classify("10","10","5","http://localhost:8088/gunsServer/static/captured/picture/pic1/6.png","3",new ArrayList<>());
        Classify classify1_2_2 = new Classify("11","11","5","http://localhost:8088/gunsServer/static/captured/picture/pic1/9.png","3",new ArrayList<>());
        Classify classify1_2_3 = new Classify("12","12","5","http://localhost:8088/gunsServer/static/captured/picture/pic1/6.png","3",new ArrayList<>());
        List classify1_2_1s = new ArrayList();
        classify1_2_1s.add(classify1_2_1);
        classify1_2_1s.add(classify1_2_2);
        classify1_2_1s.add(classify1_2_3);

        //二级导航
        Classify classify1_1 = new Classify("4","种类1","1","","2",classify1_1_1s);
        Classify classify1_2 = new Classify("5","种类2","2","","2",classify1_2_1s);


        List list1 = new ArrayList<>();
        list1.add(classify1_1);
        List list2 = new ArrayList<>();
        list2.add(classify1_2);

        //一级导航
        Classify classify1 = new Classify("1","种类1","0","","1",list1);
        Classify classify2 = new Classify("2","种类2","0","","1",list2);

        List allList = new ArrayList();
        allList.add(classify1);
        allList.add(classify2);

        Map map = new HashMap();
        map.put("list",allList);
        return ResponseData.success(map);
    }

    @CrossOrigin("*")
    @RequestMapping("test4")
    @ResponseBody
    public ResponseData test4(String keyword) {
        Details details = new Details("663","http://localhost:8088/gunsServer/static/captured/picture/pic1/9.png","标题","內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容红红火火恍恍惚惚或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容，內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容红红火火恍恍惚惚或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容,內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容红红火火恍恍惚惚或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容,內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容红红火火恍恍惚惚或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容內容。");
        Map map = new HashMap();
        map.put("list",details);
        return ResponseData.success(map);
    }


    @CrossOrigin("*")
    @RequestMapping("test1")
    public String auth1() {
        String json = "null";
        try {
            json = readJsonData("C:\\Users\\Administrator\\Documents\\HBuilderProjects\\TinyShop-UniApp 前后端开源的微商城项目\\pages\\index\\111.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        logger.info(jsonObject.getString("RFM_12_var50"));
        return jsonObject.getString("RFM_12_var50");
    }

    public static String readJsonData(String pactFile) throws IOException {
        // 读取文件数据
        //System.out.println("读取文件数据util");

        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(pactFile);//"D:"+File.separatorChar+"DStores.json"
        if (!myFile.exists()) {
            System.err.println("Can't Find " + pactFile);
        }
        try {
            FileInputStream fis = new FileInputStream(pactFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in  = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);  //new String(str,"UTF-8")
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        //System.out.println("读取文件结束util");
        return strbuffer.toString();
    }

}

