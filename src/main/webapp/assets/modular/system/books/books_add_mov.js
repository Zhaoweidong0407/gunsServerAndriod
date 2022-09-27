/**
 * 详情对话框
 */


var BooksInfoDlg = {
    data: {
        detailId: "",
        sort: "",
        filePath: "",
        productId: Feng.getUrlParam("productId")
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    form.val('booksForm', BooksInfoDlg.data);
    //让当前iframe弹层高度适应
    admin.iframeAuto();
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/books/addItem", function (data) {
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

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/books?productId=' + BooksInfoDlg.data.productId;
    });
});

var OrderInfo = {
    movUrls: ""  //用于按数组方式存放pic列表
};

layui.use(['upload', 'form'], function () {
    var form = layui.form
        , $ = layui.jquery
        , upload = layui.upload;
    var uploadInst = upload.render({
        elem: '#control_video_button_select', //绑定元素
        url: "/books/addMovItem",//上传接口
        accept: 'video',
        auto: false,//选完文件后不自动上传
        data:{
            "productId" : BooksInfoDlg.data.productId,
            "fileType" : $("#fileType").val()
        },
        bindAction: '#control_video_button_input',
        choose: function (obj) {
            console.log("choose start");
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#control_video_demo').attr('src', result);
                $('#control_video_button_input').css('display', 'inline');
                $('#control_video_demo').css('display', 'block');
                $('#control_video_del').css('display', 'inline');
                Feng.success("选择成功");
            });
            console.log("choose end");
        },
        before: function (obj) {
            console.log();
        },
        done: function (res) {
            console.log(res);
            //如果上传失败
            if (res.success){
                Feng.success("添加成功！");
            }else {
                Feng.error("添加失败!");
            }
            //上传成功
            document.getElementById("control_video").value = res.filename;
            document.getElementById("temp_url_id").value = res.msg;
            //OrderInfo.movUrls = (Feng.ctxPath + res.data.destFilePath); //添加视频路径
        }, error: function (res) {
            console.log(res);
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    $('#control_video_del').click(function () {
        $('#control_video_button_input').css('display', 'none');
        $('#control_video_demo').css('display', 'none');
        $('#control_video_del').css('display', 'none');
        $('#control_video').val('');
    })
});
