
$(document).ready(function(){
    $.ajax({
        type:"GET",
        url:"/cart/all",
        contentType:"application/json",
        success: function(response) {
            console.log("all")
            console.log(response);
            var menuList = response.menus;
            var menuBlock = "";
            var totalCount = 0;
            $.each(menuList, function(i) {
                totalCount += menuList[i].price * menuList[i].count
                menuBlock += "<tr>"
                menuBlock += "<td>" +menuList[i].name+ "</td>"
                menuBlock +="<td>" +menuList[i].price+ "</td>"
                menuBlock +="<td>" +menuList[i].count+ "</td>"
                menuBlock += "</tr>"
            });
            console.log(totalCount);
            $('#cart-table-body').append(menuBlock);
            $("#total-price").text(totalCount+ " 원");
            var divOrder = document.getElementById('order-submit');
            divOrder.innerHTML = "<a onclick=\"callOrder("+response.id+", "+totalCount+")\" type='button' class='btn btn-outline-dark'>주문하기</a>"
        }
    });
});

function callOrder(id, total) {
    if (id == null || total == 0) {
        alert("주문할 상품이 존재 하지 않습니다.");
        location.href = "/";
    } else {
        $.ajax({
            type:"POST",
            url:"order/"+id,
            contentType: "application/json",
            success: function(response) {
                alert("주문이 완료 되었습니다.")
                location.href = "/";
            },
            error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }

}