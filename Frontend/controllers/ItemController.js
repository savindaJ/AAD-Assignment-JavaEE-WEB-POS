let allItem;
$('#btnUpdateItem').on('click', function () {
    updateItem();
});

function updateItem() {
    let id = $(`#upItemId`).val();
    if (searchItem(id) === undefined) {
        alert("No such Item..please check the ID");
        $('#Item-body').empty();
    } else {
        let description = $(`#upItemdesc`).val();
        let unitPrice = $(`#upUnitPrice`).val();
        let qty = $(`#upQty`).val();
        const json = {
            itemCode: id,
            description: description,
            unitPrice: parseFloat(unitPrice),
            qty: parseInt(qty)
        }
        $.ajax({
            url: baseUrl + "item",
            type: "put",
            dataType: "json",
            data: JSON.stringify(json),
            success: function (res) {
                getAllItem();
                clearUpdateTxt();
                Swal.fire({
                    position: "top-end", icon: "success", title: res.message, showConfirmButton: false, timer: 1500
                });
            },
            error: function (err) {
                let parse = JSON.parse(err.responseText);
                alert(parse.message);
            }
        });
    }
}

$('#btnSaveItem').on('click', function () {
    saveItem();
});

function saveItem() {
    let itemId = $('#txtItemId').val();
    if (searchItem(itemId.trim()) === undefined) {
        $.ajax({
            url: baseUrl + "item", type: 'post', dataType: 'json', data: {
                itemCode: $('#txtItemId').val(),
                description: $('#txtItemdec').val(),
                unitPrice: $('#txtItemQty').val(),
                qty: $('#txtItemUnitPrice').val()
            }, success: function (res) {
                Swal.fire({
                    position: "top-end", icon: "success", title: res.message, showConfirmButton: false, timer: 1500
                });
                getAllItem();
            }, error: function (err) {
                let parse = JSON.parse(err.responseText);
                alert(parse.message);
            }
        })
    } else {
        alert('already exits Item id');
    }
    clearItemTxt();
}

function searchItem(id) {
    return allItem.find(function (item) {
        return item.itemCode === id;
    });
}


$('#btnGetAllItem').on('click', function () {
    getAllItem();
});

function getAllItem() {
    $('#Item-body').empty();

    $(`#Item-body`).append(`<tr>
                                <td>Loading !</td>
                                <td>Loading !</td>
                                <td>Loading !</td>
                                <td>Loading !</td>
                                <td>Loading !</td>
                   
                             </tr>`);

    $.ajax({
        url: baseUrl + "item", type: 'get', dataType: 'json', success: function (res) {
            allItem = res.data;
            itemDB = res.data;
            setItemIds(itemDB);
            $('#Item-body').empty();
            setStatus();
            for (const item of res.data) {
                $(`#Item-body`).append(`<tr>
                                <td>${item.itemCode}</td>
                                <td>${item.description}</td>
                                <td>${item.price}</td>
                                <td>${item.quantity}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2 btnUpdate" data-bs-toggle="modal"
                                        data-bs-target="#update-model">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm deleteItem">Delete</button></td>
                   
                             </tr>`);
            }
            $(`.btnUpdate`).on('click', function () {
                var $row = $(this).closest("tr");
                $tds = $row.find("td:nth-child(1)");
                $ts = $row.find("td:nth-child(2)");
                $tt = $row.find("td:nth-child(3)");
                $tf = $row.find("td:nth-child(4)");
                // let td_list =  $();

                $(`#upItemId`).val($tds.text());
                $(`#upItemdesc`).val($ts.text());
                $(`#upUnitPrice`).val($tt.text());
                $(`#upQty`).val($tf.text());

            });

            $('.deleteItem').on('click', function () {
                var $row = $(this).closest("tr");
                $tds = $row.find("td:nth-child(1)");
                if (searchItem($tds.text()) === undefined) {
                    alert("No such Item..please check the ID");
                } else {
                    Swal.fire({
                        title: "Are you sure to delete this item ?",
                        text: "You won't be able to revert this!",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Yes, delete it!"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            $.ajax({
                                url: baseUrl + "item?itemCode=" + $tds.text(),
                                type: "delete",
                                dataType: "json",
                                success: function (res) {
                                    getAllItem();
                                    Swal.fire({
                                        title: "Deleted!", text: res.message, icon: "success"
                                    });
                                },
                                error: function (err) {
                                    let parse = JSON.parse(err.responseText);
                                    alert(parse.message);
                                }
                            });
                        }
                    });
                }
            });
        }, error: function (err) {

        }
    });
}


function setEvent() {

    $(`.btnUpdate`).on('click', function () {

        var $row = $(this).closest("tr");
        $tds = $row.find("td:nth-child(1)");
        $ts = $row.find("td:nth-child(2)");
        $tt = $row.find("td:nth-child(3)");
        $tf = $row.find("td:nth-child(4)");
        // let td_list =  $();

        $(`#upItemId`).val($tds.text());
        $(`#upItemdesc`).val($ts.text());
        $(`#upUnitPrice`).val($tt.text());
        $(`#upQty`).val($tf.text());

    });
}

$('#txtSearchItem').on('keyup', function () {
    let txtVal = $('#txtSearchItem');

    if (txtVal.val() === '') {
        $(`#Item-body`).empty();
        getAllItem();
    }
    $(`#Item-body`).empty();

    for (let item of allItem) {
        if ($("#itemSearch").val() === "Code") {
            if (item.code.indexOf($("#txtSearchItem").val()) !== -1) {

                $("#tblItem > tbody").append($(`#Item-body`).append(`<tr>
                                 <td>${item.itemCode}</td>
                                <td>${item.description}</td>
                                <td>${item.price}</td>
                                <td>${item.quantity}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                        data-bs-target="#update-model">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm delete">Delete</button></td>
                   
                             </tr>`));
            }
        } else {
            if (item.description.indexOf($("#txtSearchItem").val()) !== -1) {

                $("#tblItem > tbody").append($(`#Item-body`).append(`<tr>
                                <td>${item.itemCode}</td>
                                <td>${item.description}</td>
                                <td>${item.price}</td>
                                <td>${item.quantity}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                        data-bs-target="#update-model">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm deleteItem">Delete</button></td>
                   
                             </tr>`));
            }
        }
    }

    setEvent();
});


getAllItem();


