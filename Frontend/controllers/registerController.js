$("#txt-email,#txt-fname,#txt-lname,#txt-pw").on("keyup", function () {
    setBtn();
});

$('#btn-signUp').on('click', function () {
    let email = $('#txt-email').val();
    let fname = $('#txt-fname').val();
    let lname = $('#txt-lname').val();
    let pw = $('#txt-pw').val();

    const user = {
        email: email,
        firstName: fname,
        lastName: lname,
        password: pw
    }

    $.ajax({
        url: baseUrl + 'user',
        type: 'post',
        contentType:'application/json',
        data:JSON.stringify(user),
        success:function (res) {
            let timerInterval;
            Swal.fire({
                title: "Registration Successfully, Please Wait...",
                html: "I will close in <b></b> milliseconds.",
                timer: 3000,
                timerProgressBar: true,
                didOpen: () => {
                    Swal.showLoading();
                    const timer = Swal.getPopup().querySelector("b");
                    timerInterval = setInterval(() => {
                        timer.textContent = `${Swal.getTimerLeft()}`;
                    }, 100);
                },
                willClose: () => {
                    clearInterval(timerInterval);
                }
            }).then((result) => {
                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer && res.message === 'success') {
                    window.location.href = "../index.html";
                }else {
                    const Toast = Swal.mixin({
                        toast: true,
                        position: "top-end",
                        showConfirmButton: false,
                        timer: 2000,
                        timerProgressBar: true,
                        didOpen: (toast) => {
                            toast.onmouseenter = Swal.stopTimer;
                            toast.onmouseleave = Swal.resumeTimer;
                        }
                    });
                    Toast.fire({
                        icon: "error",
                        title: res.message
                    });
                }
            });
        },
        error:function (err) {
            let obj  = JSON.parse(err.responseText);
            const Toast = Swal.mixin({
                toast: true,
                position: "top-end",
                showConfirmButton: false,
                timer: 2000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.onmouseenter = Swal.stopTimer;
                    toast.onmouseleave = Swal.resumeTimer;
                }
            });
            Toast.fire({
                icon: "error",
                title: obj.message
            });
        }
    });

});

$('#hyp-login').on('click', function () {
    window.location.href = "../index.html";
});