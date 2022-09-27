/**
 * 详情对话框
 */
var DetailsInfoDlg = {
    data: {
        productId: "",
        picPath: "",
        title: "",
        content: "",
        bookId: ""
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

    // //获取详情信息，填充表单
    // console.log("adasd:"+Feng.getUrlParam("productId"));
    // var ajax = new $ax(Feng.ctxPath + "/details/detailByProduct?productId=" + Feng.getUrlParam("productId"));
    // var result = ajax.start();
    // form.val('detailsForm', result.data);
    $("#productId").val(Feng.getUrlParam("productId"));

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/details/addItem", function (data) {
            Feng.success("添加成功！");
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/details?productId=" + Feng.getUrlParam("productId");
    });

    //上传产品图标图片
    var uploadInst = upload.render({
        elem: '#btnUpload' //绑定元素
        ,url: '/system/upload/' //上传接口
        ,done: function(res){
            //上传完毕回调
            $("#picPath").val(res.data.destFilename);
            Feng.success("上传成功！");
        }
        ,error: function(){
            //请求异常回调
            console.log("上传异常" + res)
            Feng.error("上传失败！");
        }
    });
});