/*
let tableBody = $("#body");


$(`#save-customer`).on('click',function () {
    // let idVal = $("#customer-gmail").val();
    /!*if (searchCustomer(idVal.trim()) === undefined) {
        let id = $("#customer-gmail");
        let address = $("#customer-address");
        let name = $("#customer-name");
        let tp = $("#customer-tp");*!/

    console.log("click !")
    $.ajax({
        url: baseUrl + "customer",
        type: "post",
        dataType: "json",
        data:{
            id:"c001"
        },
        success: function (res) {
            alert("saved !")
        },
        error: function (err) {
           /!* let parse = JSON.parse(err.responseText);
            alert(parse.message);*!/
        }
    });

    /!*getAll();
    clearCustomerInputFields();*!/
});

/!*function searchCustomer(id) {
    return customerDB.find(function (customer) {
        return customer.id == id;
    });
}

$('#updateCustomer').on('click', function () {
    updateCustomer();
});

function updateCustomer() {
    let id = $(`#upCID`).val();
    if (searchCustomer(id) == undefined) {
        alert("No such Customer..please check the ID");
    } else {
        let consent = confirm("Do you really want to update this customer.?");
        if (consent) {
            let customer = searchCustomer(id);
            //if the customer available can we update.?
            let name = $(`#upCName`).val();
            let address = $(`#upCAddress`).val();
            let tp = $(`#upCTp`).val();

            customer.name = name;
            customer.address = address;
            customer.tp = tp;
        }
    }
    getAll();
    clearUpdateFiald();
}


$(`#getAllCustomer`).click(function () {
    getAll();
});*!/


function getAll() {

    $(`#body`).empty();

    $.ajax({
        url: baseUrl + "customer",
        type: "get",
        dataType: "json",
        success: function (resp) {
            $(`#body`).empty();
            console.log(resp.message)
            console.log(resp)
            for (const customer of resp.data) {

                $(`#body`).append(`<tr>
                                <td>${customer.cusId}</td>
                                <td>${customer.cusName}</td>
                                <td>${customer.address}</td>
                                <td>${customer.salary}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2 btnEdit" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal2">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm delete">Delete</button></td>

                             </tr>`);

            }

        },
        error: function (err) {
            let parse = JSON.parse(err.responseText);
            alert(parse.message);
        }
    })

    setEvent();
}

function setEvent() {

    $(`#tblCustomer tr`).click(function () {

        var $row = $(this).closest("tr"),
            $tds = $row.find("td:nth-child(1)");
        $ts = $row.find("td:nth-child(2)");
        $tt = $row.find("td:nth-child(3)");
        $tf = $row.find("td:nth-child(4)");
        // let td_list =  $();

        $(`#upCID`).val($tds.text());
        $(`#upCName`).val($ts.text());
        $(`#upCAddress`).val($tt.text());
        $(`#upCTp`).val($tf.text());

    });

  /!*  $('.delete').click(function () {
        $(`#tblCustomer tr`).click(function () {

            var $row = $(this).closest("tr");        // Finds the closest row <tr>
            $tds = $row.find("td:nth-child(1)");

            if (searchCustomer($tds.text()) === undefined) {
                alert("No such Customer..please check the ID");
            } else {
                if (deleteFunc($tds.text())) {
                    // $(this).closest("tr").remove();
                    alert("customer Deleted !");
                    getAll();
                }
            }
        });
    });*!/


}

/!*$('.delete').click(function () {
    $(`#tblCustomer tr`).click(function () {

        var $row = $(this).closest("tr");        // Finds the closest row <tr>
        $tds = $row.find("td:nth-child(1)");

        if (searchCustomer($tds.text()) === undefined) {
            alert("No such Customer..please check the ID");
        } else {
            if (deleteFunc($tds.text())) {
                // $(this).closest("tr").remove();
                alert("customer Deleted !");
                getAll();
            }
        }
    });
});*!/

function deleteFunc(id) {
    for (let i = 0; i < customerDB.length; i++) {
        if (customerDB[i].id == id) {
            customerDB.splice(i, 1);
            return true
        }
    }
    return false;
}

$(`#tblCustomer tr`).click(function () {

    var $row = $(this).closest("tr");        // Finds the closest row <tr>
    $tds = $row.find("td:nth-child(1)");
    $ts = $row.find("td:nth-child(2)");
    $tt = $row.find("td:nth-child(3)");
    $tf = $row.find("td:nth-child(4)");
    // let td_list =  $();

    $(`#upCID`).val($tds.text());
    $(`#upCName`).val($ts.text());
    $(`#upCAddress`).val($tt.text());
    $(`#upCTp`).val($tf.text());


});

$('#txtSearch').on('keyup', function () {

    let txtVal = $('#txtSearch');

    if (txtVal.val() === '') {
        getAll();
    }

    $(`#body`).empty();
    for (let customer of customerDB) {
        if ($("#cusSearch").val() == "Customer Id") {
            if (customer.id.indexOf($("#txtSearch").val()) !== -1) {
                $("#tblCustomer > tbody").append($(`#body`).append(`<tr>
                                <td>${customer.id}</td>
                                <td>${customer.name}</td>
                                <td>${customer.address}</td>
                                <td>${customer.tp}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal2">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm delete">Delete</button></td>

                             </tr>`));
            }
        } else {
            if (customer.name.indexOf($("#txtSearch").val()) !== -1) {

                $("#tblCustomer > tbody").append($(`#body`).append(`<tr>
                                <td>${customer.id}</td>
                                <td>${customer.name}</td>
                                <td>${customer.address}</td>
                                <td>${customer.tp}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal2">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm delete">Delete</button></td>

                             </tr>`));
            }
        }
    }
});


getAll();*/

$(".btnEdit").on('click', () => {
    console.log("edit")
});

$('#save-customer').on('click', function () {
    $.ajax({
        url: baseUrl + "customer",
        type: "post",
        dataType: "json",
        data: {
            id: $('#customer-gmail').val(),
            name: $('#customer-name').val(),
            address: $('#customer-address').val(),
            salary: $('#customer-tp').val()
        },
        success: function (res) {
            getAll();
            clearCustomerInputFields();
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: res.message,
                showConfirmButton: false,
                timer: 1500
            });
        },
        error: function (err) {
            let parse = JSON.parse(err.responseText);
            alert(parse.message);
        }
    });
});

function getAll() {

    $(`#body`).empty();

    $(`#body`).append(`<tr><td>Loading !</td>
                        <td>Loading !</td>
                        <td>Loading !</td>
                        <td>Loading !</td>
                        <td>Loading !</td>
                        </tr>`);

    $.ajax({
        url: baseUrl + "customer",
        type: "get",
        dataType: "json",
        success: function (resp) {
            $(`#body`).empty();
            for (const customer of resp.data) {
                $(`#body`).append(`<tr>
                                <td>${customer.cusId}</td>
                                <td>${customer.cusName}</td>
                                <td>${customer.address}</td>
                                <td>${customer.salary}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2 btnEdit" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal2">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm delete">Delete</button></td>

                             </tr>`);

            }
            $(".btnEdit").on('click', function () {
                var $row = $(this).closest("tr");
                $tds = $row.find("td:nth-child(1)");
                $ts = $row.find("td:nth-child(2)");
                $tt = $row.find("td:nth-child(3)");
                $tf = $row.find("td:nth-child(4)");
                $(`#upCID`).val($tds.text());
                $(`#upCID`).prop('disabled', true);
                $(`#upCName`).val($ts.text());
                $(`#upCAddress`).val($tt.text());
                $(`#upCTp`).val($tf.text());
            });

            $('.delete').on('click', function () {
                var $row = $(this).closest("tr");
                $tds = $row.find("td:nth-child(1)");

                Swal.fire({
                    title: "Are you sure?",
                    text: "You won't be able to revert this!",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Yes, delete it!"
                }).then((result) => {
                    if (result.isConfirmed) {
                        $.ajax({
                            url: baseUrl + "customer?id=" + $tds.text(),
                            type: "delete",
                            dataType: "json",
                            success: function (res) {
                                getAll();
                                Swal.fire({
                                    title: "Deleted!",
                                    text: res.message,
                                    icon: "success"
                                });
                            },
                            error: function (err) {
                                let parse = JSON.parse(err.responseText);
                                alert(parse.message);
                            }
                        });
                    }
                });

            });
        },
        error: function (err) {
            let parse = JSON.parse(err.responseText);
            alert(parse.message);
        }
    })
}

$('#updateCustomer').on('click', function () {
    const customer = {
        id: $(`#upCID`).val(),
        name: $(`#upCName`).val(),
        address: $(`#upCAddress`).val(),
        salary: parseInt($(`#upCTp`).val())
    }


    $.ajax({
        url: baseUrl + "customer",
        type: "put",
        dataType: "json",
        data: JSON.stringify(customer),
        success: function (res) {
            // alert(res.message)
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: res.message,
                showConfirmButton: false,
                timer: 1500
            });
            getAll();
            clearUpdateFiald();
        },
        error: function (err) {
            alert("Bad Request !")
        }
    })
});

getAll();