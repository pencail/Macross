layui.use(['table', 'form', 'jquery'], function () {
    let table = layui.table;
    let form = layui.form;
    let $ = layui.jquery;

    form.on('submit(save)', function (data) {
        let auto = "";
        if ($('#checkbox-auto').is(':checked')) {
            auto = "true"
        } else {
            auto = "false"
        }

        $.ajax({
            url: "/updataSetting/RSSsetting",
            type: "post",
            data: {
                Dir: $('#dir').val(),
                auto: auto
            },
            success: function (flag) {
                if (flag === "true") {
                    layer.alert('修 改 成 功</br>', function () {
                        location.reload();
                    });
                }
                if (flag === "fail" || flag === "false") {
                    layer.alert('修改失败，请重试', {icon: 2});
                }
                if (flag === "fal") {
                    layer.alert('无需改动', function () {
                        location.reload();
                    });
                }
            }
        })
    })
})