layui.use(['form', 'button', 'popup', 'layer', 'jquery'], function () {
    let form = layui.form;
    let button = layui.button;
    let popup = layui.popup;
    let layer = layui.layer;
    let $ = layui.jquery;

    // 登 录 提 交
    form.on('submit(login)', function (data) {

        /// 登录
        $.ajax({
            url: "/login",
            type: "post",
            data: {
                username: $('#username').val(),
                password: $('#password').val()
            },
            success: function (flag) {
                if (flag == "true") {
                    button.load({
                        elem: '.login',
                        time: 1500,
                        done: function () {
                            popup.success("登录成功", function () {
                                location.href = "/index"
                            });
                        }
                    })
                }
                if (flag == "false") {
                    layer.msg('用户名或密码错误', {icon: 0}, function () {
                        time: 2000
                    })
                }
            }

        })

        return false;
    });
})