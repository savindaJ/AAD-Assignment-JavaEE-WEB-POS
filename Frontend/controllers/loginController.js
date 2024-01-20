$('#btn-sign-in').on('click', function () {
    let email = $('#txt-email').val();
    let pw = $('#txt-pw').val();

    console.log('text inputs : ',email,pw);

    const user = {
        email: email,
        password: pw
    }

    $.ajax({
        url: baseUrl + 'user',
        type:'post',
        contentType:'application/json',
        data: JSON.stringify(user),
        success:function (res) {
            let timerInterval;
            Swal.fire({
                title: "Send Redirect Request, Please Wait...",
                html: "I will close in <b></b> milliseconds.",
                timer: 2000,
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
                if (result.dismiss === Swal.DismissReason.timer) {
                    window.location.href = "pages/main.html";
                }
            });
        },
        error:function (err) {

        }
    });
});

$('#btn-register').on('click', function () {
    window.location.href = 'pages/signup.html';
});