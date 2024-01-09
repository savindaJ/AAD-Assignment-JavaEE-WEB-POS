/*
//Write down all the item form controllers here

//load all existing items
getAllItems();


function getAllItems() {
    //clear all tbody data before add
    $("#tblItem").empty();

    //get all items
    for (let i = 0; i < itemDB.length; i++) {
        let code = itemDB[i].code;
        let description = itemDB[i].description;
        let qty = itemDB[i].qtyOnHand;
        let unitPrice = itemDB[i].unitPrice;

        let row = `<tr>
                     <td>${code}</td>
                     <td>${description}</td>
                     <td>${qty}</td>
                     <td>${unitPrice}</td>
                    </tr>`;

        // //and then append the row to tableBody
        $("#tblItem").append(row);

        //invoke this method every time
        // we add a row // otherwise click
        //event will not work

    }
}



*/
let allItem;
$('#btnUpdateItem').on('click',function (){
    updateItem();
});

function updateItem(){
    let id = $(`#upItemId`).val();
    if (searchItem(id) == undefined) {
        alert("No such Item..please check the ID");
    } else {
        let consent = confirm("Do you really want to update this item.?");
        if (consent) {
            let item = searchItem(id);
            let description = $(`#upItemdesc`).val();
            let unitPrice = $(`#upUnitPrice`).val();
            let qty = $(`#upQty`).val();

            item.description = description;
            item.unitPrice = unitPrice;
            item.qtyOnHand = qty;

        }
    }
    getAllItem();
    clearUpdateTxt();
}

$('#btnSaveItem').on('click', function () {
    saveItem();
});

function saveItem() {
    let itemId = $('#txtItemId').val();
    if (searchItem(itemId.trim()) === undefined) {
        $.ajax({
            url:baseUrl+"item",
            type: 'post',
            dataType: 'json',
            data:{
                itemCode: $('#txtItemId').val(),
                description: $('#txtItemdec').val(),
                unitPrice: $('#txtItemQty').val(),
                qty: $('#txtItemUnitPrice').val()
            },
            success:function (res) {
                Swal.fire({
                    position: "top-end",
                    icon: "success",
                    title: res.message,
                    showConfirmButton: false,
                    timer: 1500
                });
                getAllItem();
            },
            error:function (err) {
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
        return item.code === id;
    });
}


$('#btnGetAllItem').on('click', function () {
    getAllItem();
});

function getAllItem() {
    $('#Item-body').empty();

    $.ajax({
       url: baseUrl+"item",
       type: 'get',
       dataType:'json',
       success:function (res) {
           allItem = res.data;
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
           $(`.btnUpdate`).on('click',function () {
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
       } ,
        error:function (err) {

        }
    });
    setEvent();
}


function setEvent() {

    $(`.btnUpdate`).on('click',function () {

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

    $('.deleteItem').click(function () {
        console.log("delete");
        $(`#tblItem tr`).click(function () {

            var $row = $(this).closest("tr");        // Finds the closest row <tr>
            $tds = $row.find("td:nth-child(1)");

            if (searchItem($tds.text()) === undefined) {
                alert("No such Item..please check the ID");
            } else {
                if (deleteItem($tds.text())) {
                    getAllItem();
                    alert("Item Deleted !");
                }
            }
        });
    });
}

function deleteItem(id) {
    for (let i = 0; i < itemDB.length; i++) {
        if (itemDB[i].code == id) {
            itemDB.splice(i, 1);
            return true
        }
    }
    return false;
}

$('#txtSearchItem').on('keyup',function (){
    let txtVal = $('#txtSearchItem');

    if (txtVal.val() === ''){
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


