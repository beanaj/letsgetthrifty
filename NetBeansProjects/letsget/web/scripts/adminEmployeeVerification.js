/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkID(input) {
    input.setCustomValidity('');
}

function checkName(input) {
    var name = input.value;
    if (name.length<=1||name.length>30) {
        input.setCustomValidity("Name must be between 2 and 30 characters");
    } else {
        input.setCustomValidity('');
    }
}

function checkPhone(input) {
    var phoneNum = /^\(?([0-9]{3})\)?[-]?([0-9]{3})[-. ]?([0-9]{4})$/; 
    var enteredNum = input.value;
    
    //Check if phone number is properly formatted:
    if (!(enteredNum.match(phoneNum))) {
        input.setCustomValidity('Invalid Phone Number: Must be 10 digits in XXX-XXX-XXXX format');
    } else {
        input.setCustomValidity('');
    }
}

function checkEmail(input) {
    //checking to ensure email at least has an @
        var email = input.value;

        var atSymbol = email.indexOf('@');
        if (atSymbol == -1) {
            input.setCustomValidity('Invalid Email Address');
        } else {
            error += "<b>Invalid Email Address</b><br><br>";
            valid = false;
        }
}
