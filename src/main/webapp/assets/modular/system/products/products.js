layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var form = layui.form;

    /**
     * 设备信息表管理
     */
    var Products = {
        tableId: "productsTable"
    };

    /**
     * 初始化表格的列
     */
    Products.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true,title: '产品ID'},
            {field: 'sketch', sort: true, title: '名称'},
            // {field: 'picturePath', sort: true, title: '图标路径'},
            {field: 'classifyId', sort: true, title: '分类父ID'},
            {field: 'isIndex', sort: true, templet: '#statusTpl', title: '首页显示'},
            {field: 'keyWord', sort: true, title: '搜索关键字'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    // 修改首页显示状态
    form.on('switch(isIndex)', function (obj) {
        var PId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        Products.changeIndexStatus(PId, checked);
    });
    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    Products.changeIndexStatus = function (PId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/products/setIsIndex", function (data) {
                Feng.success("设置首页显示成功!");
            }, function (data) {
                Feng.error("设置失败!");
                table.reload(Products.tableId);
            });
            ajax.set("productId", PId);
            ajax.set("isIndex", true);
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/products/setIsIndex", function (data) {
                Feng.success("隐藏首页显示成功!");
            }, function (data) {
                Feng.error("设置失败!" + data.responseJSON.message + "!");
                table.reload(Products.tableId);
            });
            ajax.set("productId", PId);
            ajax.set("isIndex", false);
            ajax.start();
        }
    };

    /**
     * 点击查询按钮
     */
    Products.search = function () {
        var queryData = {};
        // queryData['sketch'] = $("#sketch").val();
        queryData['sketch'] = $("#sketch").val();
        queryData['keyWord'] = $("#keyWord").val();
        table.reload(Products.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Products.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加设备信息表',
            area:["800px","800px"],
            content: Feng.ctxPath + '/products/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Products.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Products.exportExcel = function () {
        var checkRows = table.checkStatus(Products.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    // Products.openEditDlg = function (data) {
    //     admin.putTempData('formOk', false);
    //     top.layui.admin.open({
    //         type: 2,
    //         title: '修改设备信息表',
    //         content: Feng.ctxPath + '/products/edit?id=' + data.id,
    //         end: function () {
    //             admin.getTempData('formOk') && table.reload(Products.tableId);
    //         }
    //     });
    // };
    Products.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/products/edit?id=' + data.id;
    };

    // //跳转至修改设备详情信息页
    // Products.openEditDetail = function (data) {
    //     window.location.href = Feng.ctxPath + '/details/editByProduct?productId=' + data.id;
    // };

    //跳转至修改设备详情信息页
    Products.openEditDetail = function (data) {
        window.location.href = Feng.ctxPath + '/details?productId=' + data.id;
    };

    //跳转至修改说明书信息页
    Products.openEditBook = function (data) {
        window.location.href = Feng.ctxPath + '/books?productId=' + data.id;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Products.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/products/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Products.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Products.tableId,
        url: Feng.ctxPath + '/products/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Products.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Products.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Products.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Products.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Products.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Products.openEditDlg(data);
        } else if (layEvent === 'editDetail') {
            Products.openEditDetail(data);
        }else if (layEvent === 'editBook') {
            Products.openEditBook(data);
        } else if (layEvent === 'delete') {
            Products.onDeleteItem(data);
        }
    });
});

