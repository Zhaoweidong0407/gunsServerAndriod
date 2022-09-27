layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 产品详情表管理
     */
    var Details = {
        tableId: "detailsTable",
        productId: Feng.getUrlParam("productId")
    };

    /**
     * 初始化表格的列
     */
    Details.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'productId', hide: true,sort: true, title: '产品表ID'},
            {field: 'picPath', sort: true, title: '内容图片地址'},
            {field: 'title', sort: true, title: '详情页标题'},
            {field: 'content', sort: true, title: '内容'},
            // {field: 'bookId', sort: true, title: '说明书ID'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Details.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(Details.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Details.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加产品详情表',
            content: Feng.ctxPath + '/details/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Details.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Details.exportExcel = function () {
        var checkRows = table.checkStatus(Details.tableId);
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
    Details.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改产品详情表',
            content: Feng.ctxPath + '/details/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Details.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Details.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/details/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Details.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        window.location.href = Feng.ctxPath + '/details/addByProduct?productId=' + Details.productId;
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Details.tableId,
        url: Feng.ctxPath + '/details/list?productId=' + Details.productId ,
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Details.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Details.search();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Details.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Details.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            window.location.href = Feng.ctxPath + '/details/editByProduct?id=' + data.id + "&productId=" + data.productId ;
            // Details.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Details.onDeleteItem(data);
        }
    });
});
