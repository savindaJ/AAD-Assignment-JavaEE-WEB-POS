
$('#btn-sign-in').on('click',function () {
    let email = $('#txt-email').val();
    let pw = $('#txt-pw').val();

    const user = {
        email: email,
        password: pw
    }

    $.ajax({
        url: baseUrl + 'user',
        type: 'get',
        data:JSON.stringify(user),
        success:function (res) {
            let timerInterval;
            Swal.fire({
                title: "Send Redirect Request, Please Wait...",
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
                if (result.dismiss === Swal.DismissReason.timer || res.message === 'success') {
                    window.location.href = "../index.html";
                }else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Something went wrong!',
                    });
                }
            });
        },
        error:function (err) {
            console.log(err);
        }
    });
});

$('#btn-register').on('click',function () {
    window.location.href = 'pages/signup.html';
});