/**
 * 订单编辑对话框
 */

layui.use('upload', function(){
    var upload = layui.upload;

    //执行实例
    var uploadInst = upload.render({
        elem: '#btnUpdate' //绑定元素
            ,url: '/system/upload/' //上传接口
        ,done: function(res){
            //上传完毕回调
            console.log("上传成功" + res)
        }
        ,error: function(){
            //请求异常回调
            console.log("上传异常" + res)
        }
    });
});

var OrderInfo = {
    data: "shopTable",
    payPicUrls:[]  //用于按数组方式存放pic列表

};
/**
 * 根据商品id，决定显示元素
 * @param productId
 */
OrderInfo.showByProductId = function (productId) {
    var $ = layui.jquery;
    var isShowTimeLenght = productId ==2 || productId ==3 ||productId ==4 || productId ==5 ;
    if (isShowTimeLenght){
        $("#div_orderTime").show();
    }else {
        $("#div_orderTime").hide();
    }
    if (productId ==1) {//短信
        $("#div_shortMessageNum").show();
        $("#div_shortMessageUnit").show();
    }else {
        $("#div_shortMessageNum").hide();
        $("#div_shortMessageUnit").hide();
    }
}
/**
 * 渲染根据index渲染图片  ---列表加载图片，绑定添加查看大图、删除
 * @param index_Pic
 */
OrderInfo.renderPayPics = function (index_Pic) {
    console.log(OrderInfo.payPicUrls) ;
    var $ = layui.jquery;
    var list = OrderInfo.payPicUrls;


    console.log(index_Pic)
    //构造新图片的预览dom
    var newInput =
        '<div class="image-container" style="display: inline-block" id="container'+index_Pic+'">' +
        '<div class="delete-css">' +
        '<button id="btnImgDel'+index_Pic+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>' +
        '</div>' +
        '<img id="showImg'+index_Pic+'"  src="'+OrderInfo.payPicUrls[index_Pic]+'" title="点击放大" height="100px"  width="100px"  class="layui-upload-img uploadImgPreView"/>'+
        // '<img id="showImg'+index+'" style="width: 150px; margin:10px;cursor:pointer;"src="' + result + '" alt="' + file.name + '">' +
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
        //delete files[index_Pic];
        //delete OrderInfo.payPicUrls[index_Pic];
        OrderInfo.payPicUrls.splice(index_Pic,1);
        //todo 删除
        console.log(JSON.stringify(OrderInfo.payPicUrls));
        document.getElementById("payPicUrl").value =JSON.stringify(OrderInfo.payPicUrls);
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


    //获取详情信息，填充表单

    var ajax = new $ax(Feng.ctxPath + "/order/detail/"+ Feng.getUrlParam("orderId") );
    var result = ajax.start();
    form.val('orderForm', result.data);

    OrderInfo.data = result.data;
    //OrderInfo.payPicUrls = result.data.payPicUrls;
    for( index_Pic in OrderInfo.payPicUrls) {//
        OrderInfo.renderPayPics(index_Pic);
    }

    // OrderInfo.showByProductId(OrderInfo.data.productId);


    //渲染时间选择框

    console.log(new Date().getTime());
    laydate.render({

        elem: '#receiveTime'

    });
    laydate.render({

        elem: '#contractSignTime'

    });





    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/order/edit", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + "/order";
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });


    /**
     * 下拉框--根据选择产品动态调整界面ui元素
     */

    form.on('select(productId)', function(data){
        var productId = data.elem.selectedIndex;
        var value = data.elem.value;
        var titel = data.elem[productId].title;


        OrderInfo.showByProductId(productId)


    });
    // 点击店铺时--在弹出对话框的table中选择
    $('#shopName').click(function () {


        layer.open({
            type: 2,
            title: '店铺选择',
            area: ['1200px', '400px'],
            content: Feng.ctxPath + '/shop/shop_list_for_dlg' ,
            btn: ['确定','关闭'],
            success: function () { //yes //end//success  弹出层打开后的回调函数


            },
            yes: function (index) { //点击“确认”按钮后触发的事件 https://www.cnblogs.com/dingxu/p/9594295.html
                var iframeWindow = window['layui-layer-iframe' + index];

                var res = iframeWindow.layui.table.checkStatus('shopTable').data;
                if (res.length == 1){
                    //var res = window["layui-layer-iframe" + index].selectedData;  //通过变量获取
                    console.log(res)
                    $('#shopId').val(res[0].shopId);
                    $('#shopName').val(res[0].companyName);
                    layer.close(index);//关闭弹层

                }

            }
        });
    });
    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/order";
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
            console.log(15489798);
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                // $('#demo1').attr('src', result); //图片链接（base64）
                //$('#preview_payImgs').append('<img src="' + result + '" alt="' + file.name + '"height="100px" width="100px" class="layui-uplo   ad-img uploadImgPreView">')
            });
        }
        , done: function (res) {
            //根据上次到web返回的情况，添加到预览ui位置
            if (resultJsons == "") {
                resultJsons = JSON.stringify(res);
            } else {
                resultJsons = resultJsons + "&" + JSON.stringify(res); //多条图片记录采用“&”符号继续风格
            };
            console.log("update文件返回数据"+resultJsons);
            console.log("update文件返回数据====="+res.data.destFilePath);
            OrderInfo.payPicUrls.push(Feng.ctxPath + res.data.destFilePath) //添加到图片列表
            //更新到html对应的ui中，该控件hidel隐藏方式
            document.getElementById("payPicUrl").value =JSON.stringify(OrderInfo.payPicUrls);
            index_add = OrderInfo.payPicUrls.length -1;
            //渲染 图片列表，发现这个方式不行，主要是index_add值会变化 ：js 解决循环数组绑定事件时函数中的数组失效 https://codeleading.com/article/16942596039/
            // var newInput =
            //     '<div class="image-container" style="display: inline-block" id="container'+index_add+'">' +
            //     '<div class="delete-css">' +
            //     '<button id="btnImgDel'+index_add+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>' +
            //     '</div>' +
            //     '<img id="showImg'+index_add+'"  src="'+OrderInfo.payPicUrls[index_add]+'" title="点击放大" height="100px"  width="100px"  class="layui-upload-img uploadImgPreView"/>'+
            //     // '<img id="showImg'+index+'" style="width: 150px; margin:10px;cursor:pointer;"src="' + result + '" alt="' + file.name + '">' +
            //     '</div>';
            //
            //
            // // 添加图片 ImgPreview-预览的dom元素的id
            // ($("#preview_payImgs").append(newInput))

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