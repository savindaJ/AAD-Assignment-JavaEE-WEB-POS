const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d{4,})(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]).{8,}$/;

let vArray = new Array();

vArray.push({field: $("#txt-email"), regEx: emailRegex});
vArray.push({field: $("#txt-pw"), regEx: passwordRegex});

function clearInputFields() {
    $("#txt-email,#txt-pw").val("");
    $("#txt-email,#txt-pw").css("border", "1px solid red");
    $("#txt-email").focus();
}

function checkValidations(object) {
    if (object.regEx.test(object.field.val())) {
        setBorder(true, object)
        return true;
    }
    setBorder(false, object)
    return false;
}


$("#txt-email,#txt-pw").on("keydown keyup", function () {
    setBtn();
});

function setBorder(bol, ob) {
    if (!bol) {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid red");
        } else {
            ob.field.css("border", "1px solid #ced4da");
        }
    } else {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid green");
        } else {
            ob.field.css("border", "1px solid #ced4da");
        }
    }

}

function checkAll() {
    for (let i = 0; i < vArray.length; i++) {
        if (!checkValidations(vArray[i])) return false;
    }
    return true;
}

function setBtn() {
    if (checkAll()) {
        $("#btn-sign-in").prop("disabled", false);
    } else {
        $("#btn-sign-in").prop("disabled", true);
    }
}

$("#btn-sign-in").prop("disabled", true);