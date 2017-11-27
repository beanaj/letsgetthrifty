/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkID(input) {
    var id = input.value;
    if (isNaN(id)) {
        input.setCustomValidity("Agency ID must be integer");
    } else {
        input.setCustomValidity('');
    }
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
