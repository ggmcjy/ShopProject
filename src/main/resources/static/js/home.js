$(document).ready(function(){


   $.ajax({
       type: "GET",
       url: "/menu/all",
       contentType: "application/json", //받는 타입
       success: function(response) {
           if(response != null) {
                console.log("yes");
                var list = response
                var block = "";
                $.each(list, function(i) {
                    block += "<div class='col mb-5'>"
                    block += "<div class='card h-100'>"
                    block += "<div class='card-body p-4'>"
                    block += "<div class='text-center'>"
                    block += "<h5 class='fw-bolder'>"+list[i].name+"</h5>"
                    block += "<span>"+list[i].price+"원</span>"
                    block += "</div>"
                    block +="</div>"
                    block +="<div class='card-footer p-4 pt-0 border-top-0 bg-transparent'>"
                    block +="<div class='text-center'>"
                    block +="<a onclick=\"callFunction("+list[i].id+")\" class='btn btn-outline-dark mt-auto'>장바구니 담기</a> <br><br>"
                    block +="</div>"
                    block +="</div>"
                    block +="</div>"
                    block +="</div>"
                });
                $('#card-container').append(block);

           }
           else {
               console.log("no");
//                    $("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");
           }
       }
   });
})

function callFunction(id) {
    var cartData = {"count": 1};
    $.ajax({
        type: "POST",
        url: "/cart/"+id,
        contentType: "application/json", //보내는 타입
        data: JSON.stringify(cartData),
        success: function(response) {
            alert("해당 상품이 장바구니에 담겼습니다.");
        },
        error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            if (request.status == 403) {
                alert("로그인이 필요한 서비스입니다.");
                location.href = "/login";
            }
        }

    });
    return true;
}
