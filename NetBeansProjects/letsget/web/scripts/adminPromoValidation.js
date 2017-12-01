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

function sortTable() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.getElementsByTagName("TR");
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[3];
      y = rows[i + 1].getElementsByTagName("TD")[3];
      //check if the two rows should switch place:
      
      if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
        //if so, mark as a switch and break the loop:
        shouldSwitch= true;
        break;
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}