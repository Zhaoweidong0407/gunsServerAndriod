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

    //让当前iframe弹层高度适应
    admin.iframeAuto();

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/products/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
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