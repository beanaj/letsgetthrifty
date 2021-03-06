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
        }else if(card.length<16){
            input.setCustomValidity("Please enter a card number at least 16 digits in length");
        }else if(card.length>16){
            input.setCustomValidity("Please enter a card number no more than 16 digits in length");
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
