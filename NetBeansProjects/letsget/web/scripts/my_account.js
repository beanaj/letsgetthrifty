/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (window, document) {

    var layout   = document.getElementById('layout'),
        menu     = document.getElementById('menu'),
        menuLink = document.getElementById('menuLink'),
        content  = document.getElementById('main');

    function toggleClass(element, className) {
        var classes = element.className.split(/\s+/),
            length = classes.length,
            i = 0;

        for(; i < length; i++) {
          if (classes[i] === className) {
            classes.splice(i, 1);
            break;
          }
        }
        // The className is not found
        if (length === classes.length) {
            classes.push(className);
        }

        element.className = classes.join(' ');
    }

    function toggleAll(e) {
        var active = 'active';

        e.preventDefault();
        toggleClass(layout, active);
        toggleClass(menu, active);
        toggleClass(menuLink, active);
    }

    menuLink.onclick = function (e) {
        toggleAll(e);
    };

    content.onclick = function(e) {
        if (menu.className.indexOf('active') !== -1) {
            toggleAll(e);
        }
    };

}(this, this.document));

//check firstname to ensure valid input
function checkFN(input){
    var firstName = input.value;
    if(firstName !== null){
        if(firstName.length<=1||firstName.length>30){
            input.setCustomValidity("First Name must be between 2 and 30 characters");
        }else{
            input.setCustomValidity('');
        }
    }
}

//check lastname to ensure valid input
function checkLN(input){
    var lastName = input.value;
    if(lastName !== null){
        if(lastName.length<=1||lastName.length>30){
            input.setCustomValidity("Last Name must be between 2 and 30 characters");
        }else{
            input.setCustomValidity('');
        }
    }
}

//check lastname to ensure valid input
function checkLN(input){
    var lastName = input.value;
    if(lastName !== null){
        if(lastName.length<=1||lastName.length>30){
            input.setCustomValidity("Last Name must be between 2 and 30 characters");
        }else{
            input.setCustomValidity('');
        }
    }
}

//check phone to ensure valid input
function checkCard(input){
    var card = input.value;
    if(card !== null){
        card = card.replace(/[0-9]/g, "");
        if(card.length!=0){
            input.setCustomValidity("Please enter a card number (Only the number, omit symbols)");
        }else{
            input.setCustomValidity('');
        }
    }
}

//check zip to ensure valid input
function checkZip(input){
    var zip = input.value;
    if(zip !== null){
        if(zip.length!==5){
            input.setCustomValidity("Please enter a valid 5-digit zip code");
        }else{
            input.setCustomValidity('');
        }
    }
}

//check exp to ensure valid input
function checkExp(input){
    var exp = input.value;
    if(exp !== null){
        if(exp.length!==5){
            input.setCustomValidity("Please enter a valid expiration date MM/YY");
        }else{
            input.setCustomValidity('');
        }
    }
}

//check pass to ensure valid input
function checkPass(input){
    var pass1 = input.value;
    var pass2 = document.getElementById("p1").value;
    if(pass1 !== null){
        if(pass1!==pass2){
            if(pass1.length<5){
                input.setCustomValidity("Passwords must be at least 5 characters");
            }else{
                input.setCustomValidity("Passwords do not match");
            }
        }else{
            if(pass1.length<5){
                input.setCustomValidity("Passwords must be at least 5 characters");
            }else{
                input.setCustomValidity("");
            }
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