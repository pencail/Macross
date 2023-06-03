layui.use(['form', 'layer', 'jquery'], function () {
    let $ = layui.jquery;
    let layer = layui.layer;
    let form = layui.form;

    form.on('submit(updata)', function (data) {
        if ($('#L_pass').val() !== $('#L_repass').val()) {
            layer.alert('两次密码不一致')
        } else {
            $.ajax({
                type: 'post',
                url: '/updateAdmin',
                data: {
                    adminName: $('#adminname').val(),
                    adminPassword: $('#L_pass').val()
                },
                success: function (UpdataFlag) {
                    if (UpdataFlag == "true") {
                        layer.alert('修 改 成 功', function () {
                            location.reload();
                        });
                    }
                    if (UpdataFlag == "false") {
                        layer.msg('修改失败', {
                            time: 2000
                        })
                    }
                }
            })
        }
    })
    return false;
})