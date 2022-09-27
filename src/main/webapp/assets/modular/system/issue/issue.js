layui.use(['table', 'admin', 'form','ax','laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var form=layui.form;
    /**
     * 管理
     */
    var Issue = {
        tableId: "issueTable"
    };
    laydate.render({
        elem: '#date'
        ,type: 'month'
        ,range: true
    });
    /**
     * 初始化表格的列
     */
    Issue.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键ID'},
            {field: 'phoneNumber', sort: true, title: '电话号码',width:120,align:'center'},
            {field: 'name', sort: true, title: '姓名',width:100,align:'center'},
            {field: 'unit', sort: true, title: '单位',width:250},
            {field: 'proName', sort: true, title: '项目名称',width:250},
            {field: 'issueOpinion', sort: true, title: '问题、反馈和意见'},
            {field: 'type', sort: true, title: '类型',width:100,align:'center', templet: function (d) {
                    var type=d.type;
                    if(type=="问题反馈"){
                        return '<span style="color:red;">'+ type +'</span>'
                    }else{
                        return type;
                    }
                }},
            {field: 'status', sort: true, title: '是否解決',width:105,align:'center', templet: function (d) {
                    var status=d.status;
                    if(status=="0"){
                        return '<span style="color:red;">未解決</span>'
                    }else{
                        return '<span style="color:green;">已解決</span>'
                    }
                }},
            {field: 'date', sort: true, title: '提交时间',width:180,align:'center'},
            {align: 'center', toolbar: '#tableBar', title: '操作',width:250,align:'center'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    var type='';
    var status='';
    form.on('select(type)',function (data) {
        type=data.value;
    })
    form.on('select(status)',function (data) {
        status=data.value;
    })
    Issue.search = function () {
        var queryData = {};
        queryData['phoneNumber'] = $("#phoneNumber").val();
        queryData['name'] = $("#name").val();
        queryData['proName'] = $("#proName").val();
        queryData['unit'] = $("#unit").val();
        queryData['type'] = type;
        queryData['statusBak'] = status;
        queryData['dateScope'] = $("#date").val();
        queryData['issueOpinion'] = $("#issueOpinion").val();
        table.reload(Issue.tableId, {where: queryData});
    };

    /**
     * 弹出添加对话框
     */
    Issue.openAddDlg = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/issue/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Issue.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Issue.exportExcel = function () {
        var checkRows = table.checkStatus(Issue.tableId);
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
    Issue.openEditDlg = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改',
            content: Feng.ctxPath + '/issue/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Issue.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Issue.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/issue/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Issue.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 点击已解決
     *
     * @param data 点击按钮时候的行数据
     */
    Issue.onResolvedItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/issue/resolved", function (data) {
                Feng.success("操作成功!");
                table.reload(Issue.tableId);
            }, function (data) {
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否已解決?", operation);
    };
    Issue.onUnsolvedItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/issue/unsolved", function (data) {
                Feng.success("操作成功!");
                table.reload(Issue.tableId);
            }, function (data) {
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否未解決?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Issue.tableId,
        url: Feng.ctxPath + '/issue/list',
        page: true,
        height: "full-140",
        cellMinWidth: 100,
        cols: Issue.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Issue.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Issue.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Issue.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Issue.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Issue.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Issue.onDeleteItem(data);
        }else if (layEvent === 'resolved') {
            Issue.onResolvedItem(data);
        }else if (layEvent === 'unsolved') {
            Issue.onUnsolvedItem(data);
        }
    });
});
