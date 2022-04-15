$(document).ready(function(){

    $.ajax({
        type : "GET",
        url: "/order/all",
        contentType : "application/json",
        success: function(response) {
            console.log(response);
            var orderBlock = "";
            var totalCount = 0;
            var orderData = response;
            $.each(orderData, function(i) {
                totalCount += orderData[i].menuPrice * orderData[i].menuCount;
                orderBlock += "<tr>"
                orderBlock += "<td>" +orderData[i].menuName + "</td>"
                orderBlock +="<td>" +orderData[i].menuPrice * orderData[i].menuCount+ "</td>"
                orderBlock +="<td>" +orderData[i].menuCount+ "</td>"
                orderBlock += "</tr>"
            });

            $('#payment-order-body').append(orderBlock);
            $("#total-payment-price").text(totalCount+ " 원");
            var divOrder = document.getElementById('payment-submit');
            divOrder.innerHTML = "<a onclick=\"callPayment("+totalCount+")\" type='button' class='btn btn-outline-dark'>결제하기</a>"
        }
    });
});

function callPayment(total) {
    if (total == 0) {
        alert("결제할 내역이 없습니다.");
        location.href = "/";
    } else {
        $.ajax({
            type:"POST",
            url:"/payment",
            contentType: "application/json",
            success: function(response) {
                alert("결제가 완료 되었습니다.")
                location.href = "/";
            },
            error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }

}