layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;


    /**
     * 使用手册表管理
     */
    var Books = {
        tableId: "booksTable",
        productId: Feng.getUrlParam("productId")
    };

    /**
     * 初始化表格的列
     */
    Books.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'detailId',hide: true, sort: true, title: '产品详情表ID'},
            {field: 'productId',hide: true, sort: true, title: '产品表ID'},
            {field: 'sort', sort: true, title: '顺序'},
            {field: 'filePath', sort: true, title: '图片路径'},
            // {field: 'fileType', sort: true, title: '文件类型'},
            {
                field: 'fileType', sort: true, title: '文件类型', templet: function (d) {
                    if (d.fileType === 'P') {
                        return "图片";
                    }else if (d.fileType === 'M') {
                        return "视频";
                    }else {
                        return "未知";
                    }
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Books.search = function () {
        var queryData = {};
        queryData['productId'] = $("#productId").val();
        if ("" != queryData['productId'] && null != queryData['productId']){
            table.reload(Books.tableId, {where: queryData});
        }else {
            table.reload(Books.tableId);
        }
    };

    /**
     * 弹出添加对话框
     */
    Books.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加使用手册表',
            area:["1500px","1500px"],
            content: Feng.ctxPath + '/books/add?productId=' + Books.productId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Books.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Books.exportExcel = function () {
        var checkRows = table.checkStatus(Books.tableId);
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
    Books.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改使用手册表',
            content: Feng.ctxPath + '/books/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Books.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Books.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/books/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Books.tableId);
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
        elem: '#' + Books.tableId,
        url: Feng.ctxPath + '/books/list?productId=' + Books.productId,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Books.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Books.search();
    });

    // 添加图片按钮点击事件
    $('#btnAddPic').click(function () {
        window.location.href = Feng.ctxPath + '/books/addPic?productId=' + Books.productId;
    });

    // 添加视频按钮点击事件
    $('#btnAddMov').click(function () {
        //Books.openAddDlg();
        window.location.href = Feng.ctxPath + '/books/addMov?productId=' + Books.productId;
    });

    // 导出excel
    $('#btnExp').click(function () {
        Books.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Books.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Books.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Books.onDeleteItem(data);
        }
    });
});
