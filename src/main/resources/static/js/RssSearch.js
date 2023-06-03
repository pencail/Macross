layui.use(['form', 'layer', 'jquery', 'util'], function () {

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    $("#subscription").click(function () {

        layer.prompt({title: 'RSS自定义名称'}, function (value) {

            let rsslink = $('#rsslink').val()

            if (!checkUrl(rsslink)) {
                layer.alert("url错误")
            } else {
                //保存链接
                $.ajax({
                    url: "/SaveRss",
                    type: "post",
                    data: {
                        rsslink: $('#rsslink').val(),
                        rssname: value.toString()
                    },
                    success: function (flag) {
                        if (flag == "true") {
                            layer.alert("订阅成功", function () {
                                parent.layer.close(index);
                            })
                        }
                        if (flag == "repeat") {
                            layer.alert("订阅列表中已有此链接")
                        }
                    }
                })
            }
        });
    });

    // 搜索链接
    form.on('submit(search)', function () {

        let rsslink = $('#rsslink').val()

        if (!checkUrl(rsslink)) {
            layer.alert("url错误")
        } else {
            parent.layer.open({
                type: 2,
                title: '链接列表',
                shade: 0.1,
                maxmin: true,
                area: ['1200px', '600px'],
                content: '/RssSearch?rsslink=' + rsslink
            })
        }
    })

    function checkUrl(str) {
        var v = new RegExp('^(?!mailto:)(?:(?:http|https|ftp)://|//)(?:\\S+(?::\\S*)?@)?(?:(?:(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[0-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))|localhost)(?::\\d{2,5})?(?:(/|\\?|#)[^\\s]*)?$', 'i');
        return v.test(str);
    }

})