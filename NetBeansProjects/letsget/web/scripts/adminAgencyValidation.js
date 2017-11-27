/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateAddAgency() {
    var id = document.forms["addAgencyForm"]["new_shippingAgencyID"].value;
    if (isNaN(id)) {
        alert("Agency ID must be integer");
        return false;
    } 
}
