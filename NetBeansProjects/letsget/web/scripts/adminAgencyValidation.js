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
        input.setCustomValidity("Agency Name must be between 2 and 30 characters");
    } else {
        input.setCustomValidity('');
    }
}

//THIS DOES NOT WORK YET
function checkPhone(input) {
    var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;  
    if((input.value.match(phoneno)) {
        input.setCustomValidity(''); 
    } else {  
        input.setCustomValidity('Phone number must be either of the following:\nXXX-XXX-XXXX\nXXX.XXX.XXXX\nXXX XXX XXXX'); 
    }  
}
