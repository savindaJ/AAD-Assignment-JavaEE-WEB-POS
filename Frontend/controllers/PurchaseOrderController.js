let today = new Date().toISOString().slice(0, 10);

let customers;

let items;

$('#txtDate').css({
    color: 'green',
    fontWeight: '500'
});

$('#txtDate').val(today);

let finalTotal = 0;

let final = 0;

$('#txtOrderId').prop('disabled',true);

$('#btnClear').on('click', function () {
    clearAll();
    clearBill();
});

//place order button

$('#btnPlaceOrder').on('click', function () {
    let cash = parseFloat($('#txtCash').val());
    let balance = cash - final;
    $('#txtBalnce').val(balance);

    $('#btnPlaceOrder').prop("disabled", true);

    let list = [];

    let trList = $('#order-tbl-body > tr');

    for (const tr of trList) {
        let child = $(tr).children();
        let item = {
            itemCode: $(child[0]).text(),
            description: $(child[1]).text(),
            price: $(child[4]).text(),
            quantity: $(child[3]).text()
        }
        list.push(item);
    }

    const orderObj = {
        orderId: $("#txtOrderId").val(),
        customerId: $('#selCusId').val(),
        itemList: list
    }

    $.ajax({
        method: 'post',
        url: baseUrl + 'order',
        contentType: 'json',
        data: JSON.stringify(orderObj),
        success: function (res) {
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: res.message,
                showConfirmButton: false,
                timer: 1500
            });
            clearAll();
            // clearBill();
            loadAllOrderDetails();
            getAllItem();
            $('#order-tbl-body').empty();
            loadAllOrderDetails();
            getNextOrderId();
        },
        error: function (err) {
            let responseJson = JSON.parse(err.responseText);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: responseJson.message,
                showConfirmButton: true
            });
        }
    });
});

$('#btnAddOrder').on('click', function () {

    if ($('#getQty').val() === "") {
        alert("empty value in hear !")
        return
    }

    let price = parseFloat($('#orderItemPrice').val());

    let total = price * parseInt($('#getQty').val());

    let list = $(`#order-tbl-body > tr > td:nth-child(1)`);

    for (const td of list) {
        if ($(td).text() == $('#selItemId').val()) {
            let row = $(td).parent();
            $(row).remove();
        }
    }

    $(`#order-tbl-body`).append(`<tr>
        <td>${$('#selItemId').val()}</td>
        <td>${$('#orderItemDesc').val()}</td>
        <td>${$('#orderItemPrice').val()}</td>
        <td>${$('#getQty').val()}</td>
        <td>${total}</td>
    </tr>`);


    let totalList = $(`#order-tbl-body > tr > td:nth-child(5)`);

    finalTotal = 0;

    for (const total of totalList) {
        finalTotal += parseFloat($(total).text());
    }

    final = finalTotal;

    $('#total').text(' ' + finalTotal + '/=');

    $('#subTotal').text(finalTotal + '/=');


    $('#btnPlaceOrder').prop("disabled", false);
    $('#btnAddOrder').prop("disabled", true);
});

$('#selCusId').on('change', function () {
    let id = $('#selCusId').val();
    for (const cus of customers) {
        if (cus.cusId === id) {
            $('#orderCusName').val(cus.cusName);
            $('#orderCusAddres').val(cus.address);
            $('#orderCusTp').val(cus.salary);
        }
    }
    $('#selItemId').focus();
});


$('#selItemId').on('change', function () {
    let id = $('#selItemId').val();
    for (const item of items) {
        if (item.itemCode === id) {
            $('#orderItemDesc').val(item.description);
            $('#orderItemPrice').val(item.price);
            $('#orderQty').val(item.quantity);
        }
    }
    $('#getQty').focus();
    $('#btnAddOrder').prop("disabled", false);

});

$('#txtDiscount').on('keyup change', function () {
    let currentDiscount = parseFloat($('#txtDiscount').val());

    let subTotal = finalTotal / 100 * currentDiscount;

    final = finalTotal - subTotal;

    $('#subTotal').text(final + '/=');

});

$('#txtDiscount').val(0);

$('#selCusId').prop('disabled', true);

function setItemIds(come) {
    items = come;
    $('#selItemId').empty();
    for (const item of come) {
        $('#selItemId').append(`<option>${item.itemCode}</option>`)
    }
}

function loadCusIds(ids) {
    customers = ids;
    $('#selCusId').empty();
    for (const customer of ids) {
        $('#selCusId').append(`<option>${customer.cusId}</option>`);
    }
}

function getNextOrderId() {
    $.ajax({
        url: baseUrl + 'order',
        type: 'get',
        dataType : 'json',
        success:function (res) {
            $("#txtOrderId").val(res.message);
            $('#txtOrderId').css("border", "2px solid green");
            $('#selCusId').prop('disabled',false);
            $('#txtOrderId').prop('disabled',true);
        },
        error:function (err) {
            let responseJson = JSON.parse(err.responseText);
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: responseJson.message,
                showConfirmButton: true
            });
        }
    })
}

getNextOrderId();