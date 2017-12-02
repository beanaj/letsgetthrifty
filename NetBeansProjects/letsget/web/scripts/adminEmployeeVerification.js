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
    //if the name is null:
    if (!name) {
        input.setCustomValidity('');
    } else {
        if (name.length<=1||name.length>30) {
            input.setCustomValidity("Name must be between 2 and 30 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkPhone(input) {
    var phoneNum = /^\(?([0-9]{3})\)?[-]([0-9]{3})[-]([0-9]{4})$/; 
    var enteredNum = input.value;
    
    //Check if phone number is properly formatted:
    //if the phone number is null:
    if (!enteredNum) {
        input.setCustomValidity('');
    } else {
        if (!(enteredNum.match(phoneNum))) {
            input.setCustomValidity('Invalid Phone Number: Must be 10 digits in XXX-XXX-XXXX format');
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkEmail(input) {
    //checking to ensure email at least has an @
    var email = input.value;

    var atSymbol = email.indexOf('@');
    //if email is null:
    if (!email) {
        input.setCustomValidity('');
    } else {
        if (atSymbol == -1) {
            input.setCustomValidity('Invalid Email Address');
        } else if (email.length<=1||email.length>45){
            input.setCustomValidity("Email must be between 2 and 45 characters")
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkType(input) {
    var type = input.value;
    //if the user type is null:
    if (!type) {
        input.setCustomValidity('');
    } else {
        if (type == "u" || type == "e" || type =="m" || type == "a") {
            input.setCustomValidity('');
        } else {
            input.setCustomValidity('Please enter valid user type: \'u\' for user, \'e\' for employee, \'m\' for manager, \'a\' for admin');
        }
    }
}


//NEED TO CHECK MORE
function checkCode(input) {
    var code = input.value;
    //if code is null:
    if (!code) {
        input.setCustomValidity('');
    } else {
        
    }
}

function checkActive(input) {
    var inputVal = input.value;
    var act = Number(input.value);
    //if act is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
        //if act is a number and an integer
        if ((typeof act ==='number') && ((act%1)===0)) {
            //check to see if between 0  and 2:
            if (act >= 0 && act <= 2) {
                input.setCustomValidity('');
            } else {
                input.setCustomValidity('Invalid value: Enter \'0\' for unconfirmed, \'1\' for active, \'2\' for suspended');
            }
        } else {
            input.setCustomValidity('Invalid value: Enter \'0\' for unconfirmed, \'1\' for active, \'2\' for suspended');
        }
//        Invalid value: Enter \'0\' for unconfirmed, \'1\' for active, \'2\' for suspended
    }
}
