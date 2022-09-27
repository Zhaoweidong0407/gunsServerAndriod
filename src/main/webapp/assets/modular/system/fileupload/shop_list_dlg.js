var ShopsDlg = {
    tableId: "shopTable",   //表格id
    selectedData:null //第二种方式选择--保存行数据

};

/**
 * 第二种方式选择--保存行数据  --后期hide
 * 主要用于
 *  1：直接访问表中被选中的记录 （可以是复选框、单选框）
 *  2：直接访问table中的数据
 */
ShopsDlg.btnGetCheckData = function () {//layui获取table表单中的数据，以及对复选框、单选按钮的操作 https://www.cnblogs.com/zhuyujie/p/12794589.html

    //shopTable-->这里是table表格的Id
    //获取表单选择的复选框中的所有数据
    if (layui.table.checkStatus('shopTable').data.length ==1){
        var checkStatus = layui.table.checkStatus('shopTable').data;
        ShopsDlg.selectedData = layui.table.checkStatus('shopTable').data[0];
        //OrderInfo.selectedShip = layui.table.checkStatus('shopTable').data[0];  //可以定义上级表单中的变量OrderInfo.selectedShip用于存储选中的record
    }

    // let tableData = layui.table.cache["shopTable"]; //获取表单中的所有数据


};
layui.use(['layer', 'table', 'ax', 'laydate', 'admin'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var admin = layui.admin;




    /**
     * 商铺列表  -初始化表格的列
     */
    ShopsDlg.initColumn = function () {
        return [[
            // {type: 'checkbox'},
            {type:'radio'},
            {field: 'shopId', hide: true, sort: true, title: 'id'},
            {field: 'companyName', sort: true, title: '商铺名称'},
            {field: 'bussinessType', sort: true, title: '主营类目'},
            {field: 'clientId', title: '客户id'},

            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'createTime', sort: true, title: '创建时间', hide: true},
            {field: 'updateTime', sort: true, title: '更新时间', hide: true}

            //,{align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100 }
        ]];
    };

    /**
     * 点击查询按钮
     */
    ShopsDlg.search = function () {
        var queryData = {};
        queryData['beginTime'] = $("#beginTime").val();
        queryData['endTime'] = $("#endTime").val();
        queryData['createUser'] = $("#createUser").val();

        table.reload(ShopsDlg.tableId, {where: queryData});
    };



    //渲染时间选择框
    laydate.render({
        elem: '#beginTime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime'
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ShopsDlg.tableId,
        url: Feng.ctxPath + '/shop/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: ShopsDlg.initColumn()
    });




    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ShopsDlg.search();
    });

    // 搜索按钮点击事件
    $('#btnGetCheckData').click(function () {
        ShopsDlg.btnGetCheckData();
    });


    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        ShopsDlg.openAddCustomer();
    });

    /**
     * 点击编辑用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    ShopsDlg.onSelect = function (data) {
        ShopsDlg.selectedData = data;



    };



    // 工具条点击事件 ---测试用代码，该工具hide
    table.on('tool(' + ShopsDlg.tableId + ')', function (obj) {
        //JSON.stringify(data)  ="{"bussinessType":"鞋类222","clientId":1243,"createTime":"2020-12-15 13:43:20","companyName":"耐克京东店2222","createUser":"1338373521894236162","updateTime":"2020-12-15 17:11:12","shopId":5,"userName":"--","regularMessage":""}"
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'select'){
            ShopsDlg.onSelect(data);

            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);//关闭本当前窗口

        }
    });





});
