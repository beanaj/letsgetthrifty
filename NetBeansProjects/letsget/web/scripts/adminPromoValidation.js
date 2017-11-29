/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkID(input) {
    var inputVal = input.value;
    var id = Number(input.value);
    //if the sp is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
        //make sure id is an integer:
        if ((typeof id ==='number') && ((id%1)===0)) {
            //make sure id is greater than 0:
            if (id > 0 && (id <= (Math.pow(2,31)-1))) {
                input.setCustomValidity("");
            } else {
                input.setCustomValidity("Promo ID Must be valid integer above 0 and below 2,147,483,647")
            }
        } else {
            input.setCustomValidity('Promo ID Must be valid integer above 0');
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
        //check if number is integer or float and is b/w 0 and 100
        if ((isNaN(perc)) || (perc < 0) || (perc > 100)) {
            input.setCustomValidity('Please enter a valid percentage between 0 and 100');
        } else {
            input.setCustomValidity('');
        }
    }
}