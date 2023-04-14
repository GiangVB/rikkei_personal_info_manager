/*Password validation */
// When the user clicks on the password field, show the message box
$("#psw").on("focus", function() {
    $("#message").show();
});

// When the user clicks outside the password field, hide the message box
$("#psw").on("blur", function() {
    $("#message").hide();
});

// When the user starts to type something inside the password field
$("#psw").on("keyup", function() {
    let pswValue = $(this).val();
    // Validate lowercase letters
    let lowerCaseLetters = /[a-z]/g;
    if (pswValue.match(lowerCaseLetters)) {
        $("#letter").removeClass("invalid").addClass("valid");
    } else {
        $("#letter").removeClass("valid").addClass("invalid");
    }

    // Validate capital letters
    let upperCaseLetters = /[A-Z]/g;
    if (pswValue.match(upperCaseLetters)) {
        $("#capital").removeClass("invalid").addClass("valid");
    } else {
        $("#capital").removeClass("valid").addClass("invalid");
    }

    // Validate numbers
    let numbers = /[0-9]/g;
    if (pswValue.match(numbers)) {
        $("#number").removeClass("invalid").addClass("valid");
    } else {
        $("#number").removeClass("valid").addClass("invalid");
    }

    // Validate length
    if (pswValue.length >= 8) {
        $("#length").removeClass("invalid").addClass("valid");
    } else {
        $("#length").removeClass("valid").addClass("invalid");
    }
});

$("#signup-form").submit(function () {
    alertMessage("success", "Sign-up successfully!")
})