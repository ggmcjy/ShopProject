

$(document).ready(function(){

    $("#login").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        var loginData = {"username": username, "password":password};
        $.ajax({
            type: "POST",
            url: "/login",
            contentType: "application/json",
            data: JSON.stringify(loginData),
            success: function(response) {
                if(response != null) {
                    console.log("yes");
//                    window.location.href = "/";
                }
                else {
                    console.log("no");
//                    $("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");
                }
            }
        });
    })
})