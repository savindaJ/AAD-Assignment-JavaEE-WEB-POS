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
            loadCusIds(resp.data);
            setStatus();
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