$('#btn-sign-in').on('click', function () {
    let txtEmail = $('#txt-email').val();
    let pw = $('#txt-pw').val();

    const myUser = {
        email: txtEmail,
        password: pw
    }

    $.ajax({
        url: baseUrl + 'user',
        type:'get',
        dataType:'json',
        data: myUser,
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
                if (result.dismiss === Swal.DismissReason.timer && res.message === 'success') {
                    window.location.href = "pages/main.html";
                }else {
                    alert('invalid !');
                }
            });
        },
        error:function (err) {
            alert('error !')
        }
    });
});

$('#btn-register').on('click', function () {
    window.location.href = 'pages/signup.html';
});