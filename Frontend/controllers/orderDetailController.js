function loadAllOrderDetails(){
    $.ajax({
        url:baseUrl+'orderDetail',
        type:'get',
        dataType:'json',
        success:function (res) {
            $('#order-detail-body').empty();
            for (const detail of res.data) {

                $('#order-detail-body').append(`<tr>
                <td>${detail.orderId}</td>
                <td>${detail.customerId}</td>
                <td>${detail.itemCode}</td>
                <td>${detail.qty}</td>
                <td>${detail.date}</td>
            </tr>`);

            }
        },
        error:function (err) {
            let mes = JSON.parse(err.responseText);
            alert(mes.message);
        }
    });
}

loadAllOrderDetails();