$("#txt-email,#txt-fname,#txt-lname,#txt-pw").on("keyup", function () {
    setBtn();
});



$('#hyp-login').on('click', function () {
    window.location.href = "../index.html";
});