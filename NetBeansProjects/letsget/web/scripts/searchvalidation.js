/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchBar(input){
    
    var dis = document.getElementById('button');
    dis.disabled = true;
    var search = input.value;
    if(search !== null || search !== ""){
        dis.disabled = false;
    }
}

