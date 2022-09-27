layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 分类表管理
     */
    var Classify = {
        tableId: "classifyTable"
    };

    /**
     * 初始化表格的列
     */
    Classify.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true, title: '节点ID'},
            {field: 'pid', sort: true, title: '父节点ID'},
            {field: 'title', sort: true, title: '分类标题'},
            // {field: 'cover', sort: true, title: '图标路径'},
            {field: 'level', sort: true, title: '几级目录'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Classify.search = function () {
        var queryData = {};
        queryData['title'] = $("#title").val();
        table.reload(Classify.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Classify.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加分类表',
            content: Feng.ctxPath + '/classify/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Classify.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Classify.exportExcel = function () {
        var checkRows = table.checkStatus(Classify.tableId);
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
    Classify.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改分类表',
            content: Feng.ctxPath + '/classify/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Classify.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Classify.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/classify/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Classify.tableId);
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
        elem: '#' + Classify.tableId,
        url: Feng.ctxPath + '/classify/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Classify.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Classify.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Classify.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Classify.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Classify.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Classify.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Classify.onDeleteItem(data);
        }
    });
});
