/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkID(input) {
    var inputVal = input.value;
    var id = Number(input.value);
    
    //if id is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
        //if ID is a number and an integer
        if ((typeof id ==='number') && ((id%1)===0)) {
            input.setCustomValidity('');
        } else {
            input.setCustomValidity('Agency ID must be integer');
        }
    }
}

function checkName(input) {
    var name = input.value;
    //if name is null:
    if (!name) {
        input.setCustomValidity('');
    } else {
        if (name.length<=1||name.length>45) {
            input.setCustomValidity("Name must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkPhone(input) {
    var phoneNum = /^\(?([0-9]{3})\)?[-]([0-9]{3})[-]([0-9]{4})$/; 
    var enteredNum = input.value;
    
    //Check if phone number is properly formatted:
    //if phone is null:
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
