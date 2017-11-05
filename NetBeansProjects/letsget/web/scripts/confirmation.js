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
