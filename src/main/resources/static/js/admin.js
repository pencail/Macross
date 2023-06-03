layui.use(['admin', 'jquery', 'popup', 'drawer'], function () {
    let $ = layui.jquery;
    let admin = layui.admin;
    let popup = layui.popup;

    admin.setConfigType("yml");
    admin.setConfigPath("../config/pear.config.yml");

    admin.render();

    // 消息点击回调
    admin.message(function (id, title, context, form) {
    });
})