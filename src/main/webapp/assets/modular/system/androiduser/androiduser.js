layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var form = layui.form;

    /**
     * 管理
     */
    var Androiduser = {
        tableId: "androiduserTable"
    };

    /**
     * 初始化表格的列
     */
    Androiduser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'mobile', sort: true, title: '电话'},
            // {field: 'password', sort: true, title: '密码'},
            {field: 'nickname', sort: true, title: '姓名'},
            {field: 'idcode', sort: true, title: '军官证号'},
            {field: 'state', sort: true, templet: '#statusTpl', title: '是否可用'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Androiduser.search = function () {
        var queryData = {};
        queryData['mobile'] = $("#mobile").val();
        table.reload(Androiduser.tableId, {where: queryData});
    };

    // 修改首页显示状态
    form.on('switch(state)', function (obj) {
        var id = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        Androiduser.changeStatus(id, checked);
    });

    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    Androiduser.changeStatus = function (id, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/androiduser/setStatus", function (data) {
                Feng.success("设置成功!");
                table.reload(Androiduser.tableId);
            }, function (data) {
                Feng.error("设置失败!");
            });
            ajax.set("id", id);
            ajax.set("state", true);
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/androiduser/setStatus", function (data) {
                Feng.success("设置成功!");
                table.reload(Androiduser.tableId);
            }, function (data) {
                Feng.error("设置失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", id);
            ajax.set("state", false);
            ajax.start();
        }
    };
    /**
     * 弹出添加对话框
     */
    Androiduser.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/androiduser/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Androiduser.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Androiduser.exportExcel = function () {
        var checkRows = table.checkStatus(Androiduser.tableId);
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
    Androiduser.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改',
            content: Feng.ctxPath + '/androiduser/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Androiduser.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Androiduser.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/androiduser/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Androiduser.tableId);
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
        elem: '#' + Androiduser.tableId,
        url: Feng.ctxPath + '/androiduser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Androiduser.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Androiduser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Androiduser.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Androiduser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Androiduser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Androiduser.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Androiduser.onDeleteItem(data);
        }
    });
});
