const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const nameRegex = /^[a-zA-Z]+(?:[-'` ]?[a-zA-Z]+)*$/;
const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d{4,})(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]).{8,}$/;

let vArray = new Array();
vArray.push({field: $("#txt-email"), regEx: emailRegex});
vArray.push({field: $("#txt-fname"), regEx: nameRegex});
vArray.push({field: $("#txt-lname"), regEx: nameRegex});
vArray.push({field: $("#txt-pw"), regEx: passwordRegex});

function clearInputFields() {
    $("#txt-email,#txt-fname,#txt-lname,#txt-pw").val("");
    $("#txt-email,#txt-fname,#txt-lname,#txt-pw").css("border", "1px solid #ced4da");
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
        $("#btn-signUp").prop("disabled", false);
    } else {
        $("#btn-signUp").prop("disabled", true);
    }
}

$("#btn-signUp").prop("disabled", true);
