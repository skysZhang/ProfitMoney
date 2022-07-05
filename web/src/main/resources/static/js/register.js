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

//注册协议确认
$(function () {
    $("#agree").click(function () {
        var ischeck = document.getElementById("agree").checked;
        if (ischeck) {
            $("#btnRegist").attr("disabled", false);
            $("#btnRegist").removeClass("fail");
        } else {
            $("#btnRegist").attr("disabled", "disabled");
            $("#btnRegist").addClass("fail");
        }
    });

    $("#phone").blur(function () {
        let phone = $.trim($("#phone").val());

        // 1.not null
        if (phone == null || phone == "") {
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
            if (data.code == 1) {
                showSuccess("phone");
            }
            if (data.code == 0) {
                showError("phone", data.message);
            }
        })

        //success
        showSuccess("phone");
    })

});
