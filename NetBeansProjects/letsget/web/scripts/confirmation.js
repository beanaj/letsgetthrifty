/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkAccount(input){
    var acc = input.value;
    if(acc !== null){
        if(acc.length<6){
            input.setCustomValidity("Invalid AccountID");
        }else{
            input.setCustomValidity('');
        }
    }
}

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
