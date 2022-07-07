//错误提示
function showError(id, msg) {
    $("#" + id + "Ok").hide();
    $("#" + id + "Err").html("<i></i><p>" + msg + "</p>");
    $("#" + id + "Err").show();
    $("#" + id).addClass("input-red");
}

//错误隐藏
function hideError(id) {
    $("#" + id + "Err").hide();
    $("#" + id + "Err").html("");
    $("#" + id).removeClass("input-red");
}

//显示成功
function showSuccess(id) {
    $("#" + id + "Err").hide();
    $("#" + id + "Err").html("");
    $("#" + id + "Ok").show();
    $("#" + id).removeClass("input-red");
}


//打开注册协议弹层
function alertBox(maskid, bosid) {
    $("#" + maskid).show();
    $("#" + bosid).show();
}

//关闭注册协议弹层
function closeBox(maskid, bosid) {
    $("#" + maskid).hide();
    $("#" + bosid).hide();
}

$(function () {
    //注册协议确认
    $("#agree").click(function () {
        const ischeck = document.getElementById("agree").checked;
        if (ischeck) {
            $("#btnRegist").attr("disabled", false);
            $("#btnRegist").removeClass("fail");
        } else {
            $("#btnRegist").attr("disabled", "disabled");
            $("#btnRegist").addClass("fail");
        }
    });

    let tag_phone = 0;
    $("#phone").blur(function () {
        let phone = $.trim($("#phone").val());
        tag_phone = 0;
        // 1.not null
        if (phone == null || phone === "") {
            showError("phone", "手机号码不能为空");
            return;
        }

        // 2.length
        if (phone.length !== 11) {
            showError("phone", "手机号码长度不正确");
            return;
        }

        // 3.format
        if (!/^1[1-9]\d{9}$/.test(phone)) {
            showError("phone", "手机号码格式错误")
            return;
        }

        $.get("/web/loan/page/checkPhone", {phone: phone}, function (data) {
            if (data.code === 1) {
                tag_phone = 1;
                showSuccess("phone");
                return;
            }
            if (data.code === 0) {
                tag_phone = 0;
                showError("phone", data.message);

            }
        });
        //success
        showSuccess("phone");
        tag_phone = 1;
        return true;
    });

    let tag_loginPassword = 0;
    $("#loginPassword").blur(function () {
        let loginPassword = $.trim($("#loginPassword").val());
        tag_loginPassword = 0;
        //not null
        if (loginPassword == null || loginPassword === "") {
            showError("loginPassword", "密码不能为空")
            return;
        }
        //length
        if (loginPassword.length < 6 || loginPassword.length > 20) {
            showError("loginPassword", "密码长度不正确");
        }
        //format
        if (!/^[0-9a-zA-Z]+$/.test(loginPassword)) {
            showError("loginPassword", "密码只可使用数组和大小写字母")
            return;
        }
        //format
        if (!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(loginPassword)) {
            showError("loginPassword", "密码应同时包含英文和数字")
            return;
        }
        //success
        showSuccess("loginPassword");
        tag_loginPassword = 1;
        return true;
    });

    $("#btnRegist").click(function () {
        if (tag_phone === 1 && tag_loginPassword === 1) {
            let phone = $.trim($("#phone").val());
            let loginPassword = $.trim($("#loginPassword").val());
            $.post("/web/loan/page/registerSubmit", {
                phone: phone, loginPassword: $.md5(loginPassword)
            }, function (data) {
                if (data.code === 0) {
                    alert(data.message)
                }
                if(data.code === 1)
                {
                    window.location.href = "/web/index"
                }
            });
        }
    });
});
