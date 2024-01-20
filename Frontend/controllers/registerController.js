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
            console.log(res);
        },
        error:function (err) {
            console.log(err);
        }
    });

});

$('#hyp-login').on('click', function () {
    window.location.href = "../index.html";
});