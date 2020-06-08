<!-- 正则验证 start-->


<!-- 正则验证 end-->

function login() {
    var name = $("#name").val();
    var password = $("#password").val();
    if (name==null) {
        showErrorInfo("请输入用户名!");
        return;
    }
    if (password==null) {
        showErrorInfo("请输入密码!");
        return;
    }

    var data = {"name": name, "password": password}
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "users/login",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            if (result != null) {
                $('.alert-danger').css("display", "none");
                setCookie(result.token);
                window.location.href = "/";
            }

            if (result == null) {
                showErrorInfo("登陆失败!请检查账号和密码！");
                return;
            }
        },
        error: function () {
            $('.alert-danger').css("display", "none");
            showErrorInfo("接口异常，请联系管理员！");
            return;
        }
    });
}


<!-- cookie操作 start-->

/**
 * 写入cookie
 *
 * @param name
 * @param value
 */
function setCookie(name) {
    let cookie = name ;
    document.cookie = cookie;
}


/**
 * 检查cookie
 */
function checkCookie() {
    var data = {"token":document.cookie};
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "users/check",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (result){
            if(result != 1){
                window.location.href = "/login.html";
            }
        }
    });
}

function showErrorInfo(info) {
    $('.alert-danger').css("display", "block");
    $('.alert-danger').html(info);
}
