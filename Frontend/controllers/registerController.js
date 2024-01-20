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
        data:JSON.stringify(user),
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
                    window.location.href = "../pages/main.html";
                }
            });
        },
        error:function (err) {
            console.log(err);
        }
    });

});

$('#hyp-login').on('click', function () {
    window.location.href = "../index.html";
});