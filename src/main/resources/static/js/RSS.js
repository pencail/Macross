layui.use(['table', 'form', 'jquery'], function () {
    let table = layui.table;
    let form = layui.form;
    let $ = layui.jquery;

    let cols = [
        [
            {type: 'checkbox'},
            {title: 'Id', field: 'rssId', align: 'center', width: 20},
            {title: '名称', field: 'rssName', align: 'center', width: 100},
            {title: 'RSS title', field: 'rssTitle', align: 'center', width: 150},
            {title: '链接', field: 'rssLink', align: 'center', width: 800},
            {title: '操作', toolbar: '#role-bar', align: 'center', width: 150}
        ]
    ]

    table.render({
        elem: '#role-table',
        url: "/RssLink",
        method: 'post',
        contentType: 'application/json',
        cols: cols,
        skin: 'line',
        toolbar: '#role-toolbar',
        defaultToolbar: [{
            title: '刷新',
            layEvent: 'refresh',
            icon: 'layui-icon-refresh',
        }, 'filter', 'print', 'exports'],
        parseData: function (o) {
            return {
                "code": 0,
                "data": o
            }
        }
    });

    table.on('tool(role-table)', function (obj) {
        if (obj.event === 'remove') {
            window.remove(obj);
        } else if (obj.event === 'view') {
            window.view(obj);
        }
    });

    table.on('toolbar(role-table)', function (obj) {
        if (obj.event === 'add') {
            window.add();
        } else if (obj.event === 'refresh') {
            window.refresh();
        } else if (obj.event === 'batchRemove') {
            window.batchRemove(obj);
        }
    });

    // 新增链接
    window.add = function () {
        layer.open({
            type: 2,
            title: '查询',
            shade: 0.1,
            area: ['600px', '400px'],
            content: '/search'
        });
    }

    // 查看链接
    window.view = function (obj) {
        let rsslink = obj.data['rssLink']
        layer.open({
            type: 2,
            title: '链接列表',
            shade: 0.1,
            maxmin: true,
            area: ['1200px', '600px'],
            content: '/RssSearch?rsslink=' + rsslink
        });
    }

    // 删除链接
    window.remove = function (obj) {
        layer.confirm('确定要删除该角色', {icon: 3, title: '提示'}, function (index) {
            layer.close(index);
            let loading = layer.load();

            $.ajax({
                url: '/deleteRSS',
                type: 'post',
                data: {
                    rssId: obj.data['rssId']
                },
                success: function (result) {
                    layer.close(loading);
                    if (result == "true") {
                        layer.msg("删除成功", {icon: 1, time: 1000}, function () {
                            obj.del();
                        });
                    } else if (result == "false") {
                        layer.msg("传删除失败", {icon: 2, time: 1000});
                    }
                }
            })
        });
    }

    // 批量删除
    window.batchRemove = function (obj) {
        let data = table.checkStatus(obj.config.id).data;

        if (data.length === 0) {
            layer.msg("未选中数据", {icon: 3, time: 1000});
            return false;
        }
        let ids = [];
        for (let i = 0; i < data.length; i++) {
            // ids += data[i].rssId+",";
            ids.push(data[i].rssId)
        }

        layer.confirm('确定要删除这些订阅', {icon: 3, title: '提示'}, function (index) {
            layer.close(index);
            let loading = layer.load();
            $.ajax({
                url: "/deleteRSSAll",
                type: 'post',
                data: {
                    id: ids,
                },
                success: function (result) {
                    layer.close(loading);
                    if (result == "true") {
                        layer.msg("删除成功", {icon: 1, time: 1000}, function () {
                            table.reload('role-table');
                        });
                    } else if (result == "false") {
                        layer.msg("传删除失败", {icon: 2, time: 1000});
                    }
                }
            })
        });
    }

    // 刷新表格
    window.refresh = function (param) {
        table.reload('role-table');
    }
})