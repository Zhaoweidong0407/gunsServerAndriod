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
});

/**
 * 订单编辑对话框
 */

var OrderInfo = {
    data: "shopTable",
    payPicUrls:[],  //用于按数组方式存放pic列表
    picNames:[]  //用于按数组方式存放pic列表

};
/**
 * 渲染根据index渲染图片  ---列表加载图片，绑定添加查看大图、删除
 * @param index_Pic
 */
OrderInfo.renderPayPics = function (index_Pic) {
    var $ = layui.jquery;
    console.log(index_Pic)
    //构造新图片的预览dom
    var newInput =
        '<div class="image-container" style="display: inline-block" id="container'+index_Pic+'">' +
        '<div class="delete-css">' +
        '<button id="btnImgDel'+index_Pic+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>' +
        '</div>' +
        '<img id="showImg'+index_Pic+'"  src="'+OrderInfo.payPicUrls[index_Pic]+'" title="点击放大" height="100px"  width="100px"  class="layui-upload-img uploadImgPreView"/>'+
        '</div>';

    // 添加图片 ImgPreview-预览的dom元素的id
    ($("#preview_payImgs").append(newInput))
    //某图片放大预览
    $("#showImg"+index_Pic).bind('click',function () {
        var width = $("#showImg"+index_Pic).width();
        var height = $("#showImg"+index_Pic).height();
        var scaleWH = width/height;
        var bigH = 600;
        var bigW = scaleWH*bigH;
        if(bigW>900){
            bigW = 900;
            bigH = bigW/scaleWH;
        }

        // 放大预览图片
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: true,
            area: [bigW + 'px', bigH + 'px'], //宽高
            content: "<img width='"+bigW+"' height='"+bigH+"' src=" + OrderInfo.payPicUrls[index_Pic] + " />"
        });
    });

    //删除某图片
    $("#btnImgDel"+index_Pic).bind('click', function () {
        OrderInfo.payPicUrls.splice(index_Pic,1);
        //123456
        OrderInfo.picNames.splice(index_Pic,1);
        document.getElementById("filePath").value =JSON.stringify(OrderInfo.payPicUrls);
        //123456
        document.getElementById("filePath").value =JSON.stringify(OrderInfo.picNames);
        $("#container"+index_Pic).remove();
    });


};



layui.use(['form', 'ax', 'laydate', "upload",'element'], function () {

    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var layer = layui.layer;
    var laydate = layui.laydate;
    var upload = layui.upload;
    //渲染时间选择框
    console.log(new Date().getTime());
    laydate.render({

        elem: '#receiveTime'

    });
    laydate.render({
        elem: '#contractSignTime'
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + '/books?productId=' + BooksInfoDlg.data.productId;
    });
    var resultJsons = "";
    //拖拽上传
    var uploadInst = upload.render({//用LAYUI实现表单提交（包含图片）-https://www.freesion.com/article/5555423527/
        elem: '#headImg'
        , url: '/system/upload/'
        , size: 1000
        , multiple: true
        , number: 7
        , before: function (obj) {
        }
        , done: function (res) {
            //根据上次到web返回的情况，添加到预览ui位置
            if (resultJsons == "") {
                resultJsons = JSON.stringify(res);
            } else {
                resultJsons = resultJsons + "&" + JSON.stringify(res); //多条图片记录采用“&”符号继续风格
            };
            OrderInfo.payPicUrls.push(Feng.ctxPath + res.data.destFilePath) //添加到图片列表
            //123456
            OrderInfo.picNames.push(Feng.ctxPath + res.data.destFilename) //添加到图片列表
            //更新到html对应的ui中，该控件hidel隐藏方式
            //document.getElementById("filePath").value =JSON.stringify(OrderInfo.payPicUrls);
            //123456
            document.getElementById("filePath").value =JSON.stringify(OrderInfo.picNames);
            index_add = OrderInfo.payPicUrls.length -1;
            OrderInfo.renderPayPics(OrderInfo.payPicUrls.length -1);
            localStorage.setItem("payPicUrls", resultJsons);//本地保存，不过后期没用用的
        }
        , error: function () {//目前没用处理重新update的问题
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
});