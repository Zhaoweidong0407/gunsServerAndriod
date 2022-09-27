/**
 * 详情对话框
 */
var ProductsInfoDlg = {
    data: {
        sketch: "",
        picturePath: "",
        classifyId: "",
        isIndex: "",
        keyWord: ""
    }
};

layui.use(['form', 'admin', 'ax','upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;
    // //让当前iframe弹层高度适应
    // admin.iframeAuto();

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/products/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('productsForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/products/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/products";
    });

    //上传产品图标图片
    var uploadInst = upload.render({
        elem: '#btnUpload' //绑定元素
        ,url: '/system/upload/' //上传接口
        ,done: function(res){
            //上传完毕回调
            $("#picturePath").val(res.data.destFilename);
            Feng.success("上传成功！");
        }
        ,error: function(){
            //请求异常回调
            console.log("上传异常" + res)
            Feng.error("上传失败！");
        }
    });
});