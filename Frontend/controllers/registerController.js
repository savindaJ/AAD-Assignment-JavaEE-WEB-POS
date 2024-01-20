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
        fname: fname,
        lname: lname,
        pw: pw
    }

    $.ajax({
        url: 'http://localhost:8080/app/user',
        type: 'get'
    });

});

$('#hyp-login').on('click', function () {
    window.location.href = "../index.html";
});