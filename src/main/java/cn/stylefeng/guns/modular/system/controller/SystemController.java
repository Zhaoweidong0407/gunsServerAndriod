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
package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.constant.DefaultAvatar;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.util.FIleNameUtil;
import cn.stylefeng.guns.modular.system.entity.FileInfo;
import cn.stylefeng.guns.modular.system.entity.Notice;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.service.FileInfoService;
import cn.stylefeng.guns.modular.system.service.NoticeService;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ???????????????
 *
 * @author fengshuonan
 * @Date 2017???2???17???20:27:22
 */
@Controller
@RequestMapping("/system")
@Slf4j
public class SystemController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private GunsProperties gunsProperties;

    /**
     * ???????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/console")
    public String console() {
        return "/modular/frame/console.html";
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/console2")
    public String console2() {
        return "/modular/frame/console2.html";
    }

    /**
     * ?????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:06 PM
     */
    @RequestMapping("/notice")
    public String hello() {
        List<Notice> notices = noticeService.list();
        super.setAttr("noticeList", notices);
        return "/modular/frame/notice.html";
    }

    /**
     * ?????????
     *
     * @author fengshuonan
     * @Date 2019/1/24 3:38 PM
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "/modular/frame/welcome.html";
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2019/1/24 3:38 PM
     */
    @RequestMapping("/theme")
    public String theme() {
        return "/modular/frame/theme.html";
    }

    /**
     * ???????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return "/modular/frame/password.html";
    }

    /**
     * ??????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/message")
    public String message() {
        return "/modular/frame/message.html";
    }

    /**
     * ?????????????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Long userId = ShiroKit.getUserNotNull().getId();
        User user = this.userService.getById(userId);

        model.addAllAttributes(BeanUtil.beanToMap(user));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
        LogObjectHolder.me().set(user);
        return "/modular/frame/user_info.html";
    }

    /**
     * ???????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/commonTree")
    public String deptTreeList(@RequestParam("formName") String formName,
                               @RequestParam("formId") String formId,
                               @RequestParam("treeUrl") String treeUrl, Model model) {

        if (ToolUtil.isOneEmpty(formName, formId, treeUrl)) {
            throw new RequestEmptyException("????????????????????????");
        }

        try {
            model.addAttribute("formName", URLDecoder.decode(formName, "UTF-8"));
            model.addAttribute("formId", URLDecoder.decode(formId, "UTF-8"));
            model.addAttribute("treeUrl", URLDecoder.decode(treeUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RequestEmptyException("????????????????????????");
        }

        return "/common/tree_dlg.html";
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/11/9 12:45 PM
     */
    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public Object uploadAvatar(@RequestParam String avatar) {

        if (ToolUtil.isEmpty(avatar)) {
            throw new RequestEmptyException("??????????????????");
        }

        avatar = avatar.substring(avatar.indexOf(",") + 1);

        fileInfoService.uploadAvatar(avatar);

        return SUCCESS_TIP;
    }

    /**
     * ????????????
     *
     * @author fengshuonan
     * @Date 2018/11/9 12:45 PM
     */
    @RequestMapping("/previewAvatar")
    @ResponseBody
    public Object previewAvatar(HttpServletResponse response) {

        ShiroUser currentUser = ShiroKit.getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        //???????????????????????????id
        User user = userService.getById(currentUser.getId());
        String avatar = user.getAvatar();

        //????????????id????????????????????????
        if (ToolUtil.isEmpty(avatar)) {
            avatar = DefaultAvatar.BASE_64_AVATAR;
        } else {
            FileInfo fileInfo = fileInfoService.getById(avatar);
            if (fileInfo == null) {
                avatar = DefaultAvatar.BASE_64_AVATAR;
            } else {
                avatar = fileInfo.getFileData();
            }
        }

        //????????????????????????
        try {
            response.setContentType("image/jpeg");
            byte[] decode = Base64.decode(avatar);
            response.getOutputStream().write(decode);
        } catch (IOException e) {
            log.error("???????????????????????????", avatar);
            throw new ServiceException(CoreExceptionEnum.SERVICE_ERROR);
        }

        return null;
    }

    /**
     * ????????????????????????
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:59 PM
     */
    @RequestMapping("/currentUserInfo")
    @ResponseBody
    public ResponseData getUserInfo() {

        ShiroUser currentUser = ShiroKit.getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        User user = userService.getById(currentUser.getId());
        Map<String, Object> map = UserFactory.removeUnSafeFields(user);

        HashMap<Object, Object> hashMap = CollectionUtil.newHashMap();
        hashMap.putAll(map);
        hashMap.put("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        hashMap.put("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));

        return ResponseData.success(hashMap);
    }

    /**
     * layui???????????? ????????????????????????
     *
     * @author fengshuonan
     * @Date 2019-2-23 10:48:29
     */
    @RequestMapping(method = RequestMethod.POST, path = "/uploadOld")
    @ResponseBody
    public ResponseData layuiUpload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        return ResponseData.success(0, "????????????", map);
    }

    /**
     * layui???????????? ????????????????????????
     *
     * @author ZhaoWD
     * @Date 2022-08-20 10:48:29
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public ResponseData uploadPic(@RequestPart("file") MultipartFile picture) {
        String pictureName = UUID.randomUUID().toString() + "--" + FIleNameUtil.getFileSuffix(picture.getOriginalFilename()) + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        map.put("destFilename", pictureName);
        map.put("destFilePath", gunsProperties.getFileResouureStaticMap() + pictureName);
        return ResponseData.success(0, "????????????", map);
    }

    @RequestMapping(value = "/UploadVedio", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResponseData UploadVedio(@RequestParam(value = "file") MultipartFile picture) {
        String pictureName = UUID.randomUUID().toString() + "--" + FIleNameUtil.getFileSuffix(picture.getOriginalFilename()) + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        map.put("destFilename", pictureName);
        map.put("destFilePath", gunsProperties.getFileResouureStaticMap() + pictureName);
        return ResponseData.success(0, "????????????", map);
    }


}
