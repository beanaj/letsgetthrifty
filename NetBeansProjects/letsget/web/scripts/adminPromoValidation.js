/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//CHECK IF INT!!
function checkID(input) {
    var id = input.value;
    //if the sp is null:
    if (!id) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(id)) || (id < 0)) {
            input.setCustomValidity("Promo ID Must be valid number");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkPromoName(input) {
    var pName = input.value;
    //if the name is null:
    if (!pName) {
        input.setCustomValidity('');
    } else {
        if (pName.length<=1||pName.length>45) {
            input.setCustomValidity("Promo name must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkPerc(input) { 
    var perc = input.value;
    //if the name is null:
    if (!perc) {
        input.setCustomValidity('');
    } else {
        //check if number is integer or float and is b/w 0 and 5
        if ((isNaN(perc)) || (perc < 0) || (rating > 100)) {
            input.setCustomValidity('Please enter a valid number between 0 and 100');
        } else {
            input.setCustomValidity('');
        }
    }
}