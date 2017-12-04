/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkISBN(input) {
//Check if ISBN is 13 digits and an integer:
    var inputVal = input.value;
    var isbn = Number(input.value);
    //if isbn is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
//if isbn is a number and an integer
        if ((typeof isbn === 'number') && ((isbn % 1) === 0)) {
//Now check to see if it's 13 digits:
            if (!(isbn.toString().length == 13)) {
                input.setCustomValidity('ISBN must be a 13 digit integer')
            } else {
                input.setCustomValidity('');
            }
        } else {
            input.setCustomValidity('ISBN must be a 13 digit integer');
        }
    }
}

function checkGenre(input) {
    var genre = input.value;
    //if the name is null:
    if (!genre) {
        input.setCustomValidity('');
    } else {
        if (genre.length <= 1 || genre.length > 45) {
            input.setCustomValidity("Genre must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkAuthor(input) {
    var author = input.value;
    //if the name is null:
    if (!author) {
        input.setCustomValidity('');
    } else {
        if (author.length <= 1 || author.length > 45) {
            input.setCustomValidity("Author's name must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkTitle(input) {
    var title = input.value;
    //if the name is null:
    if (!title) {
        input.setCustomValidity('');
    } else {
        if (title.length <= 1 || title.length > 45) {
            input.setCustomValidity("Title must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkRating(input) {
    var rating = input.value;
    //if the name is null:
    if (!rating) {
        input.setCustomValidity('');
    } else {
//check if number is integer or float and is b/w 0 and 5
        if ((isNaN(rating)) || (rating < 0) || (rating > 5)) {
            input.setCustomValidity('Please enter a valid number between 0 and 5');
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkLink(input) {
    input.setCustomValidity('');
}

function checkEdition(input) {
    var inputVal = input.value;
    var edition = Number(input.value);
    //if the edition is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
//make sure edition is integer and 
        if ((typeof edition === 'number') && ((edition % 1) === 0)) {
//Now make sure edition is between 1 and 7:
            if (edition >= 1 && edition <= 15) {
                input.setCustomValidity('');
            } else {
                input.setCustomValidity('Please enter a valid integer between 1 and 15');
            }
        } else {
            input.setCustomValidity('Please enter a valid number');
        }
    }
}

function checkPublisher(input) {
    var pub = input.value;
    //if the name is null:
    if (!pub) {
        input.setCustomValidity('');
    } else {
        if (pub.length <= 1 || pub.length > 45) {
            input.setCustomValidity("Publisher name must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkPubYear(input) {
    var inputVal = input.value;
    var pubYear = Number(input.value);
    //if the pubYear is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
//make sure pubYear is an integer
        if ((typeof pubYear === 'number') && ((pubYear % 1) === 0)) {
            if (pubYear >= 0 && pubYear <= 2300) {
                input.setCustomValidity('');
            } else {
                input.setCustomValidity('Please enter a year between 0 and 2300');
            }
        } else {
            input.setCustomValidity('Please enter a year between 0 and 2300');
        }
    }
}

function checkQuantity(input) {
    var inputVal = input.value;
    var quantity = Number(input.value);
    //if the quantity is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
//check if quantity is integer:
        if ((typeof quantity === 'number') && ((quantity % 1) === 0)) {
//make sure quantity is 1 or greater:
            if (quantity >= 1 && quantity <= (Math.pow(2, 31) - 1)) {
                input.setCustomValidity('');
            } else {
                input.setCustomValidity("Quantity must be valid number greater than 0");
            }
        } else {
            input.setCustomValidity('Quantity must be valid number greater than 0');
        }
    }
}

function checkThreshold(input) {
    var inputVal = input.value;
    var thresh = Number(input.value);
    //if the quantity is null:
    if (!inputVal) {
        input.setCustomValidity('');
    } else {
//check if min threshold is integer:
        if ((typeof thresh === 'number') && ((thresh % 1) === 0)) {
//make sure thresh is 1 or greater:
            if (thresh >= 1 && thresh <= (Math.pow(2, 31) - 1)) {
                input.setCustomValidity('');
            } else {
                input.setCustomValidity("Min Threshold must be valid number greater than 0");
            }
        } else {
            input.setCustomValidity('Min Threshold must be valid number greater than 0');
        }
    }
}

function checkBuyP(input) {
    var bp = input.value;
    //if the quantity is null:
    if (!bp) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(bp)) || (bp < 0)) {
            input.setCustomValidity("Buy Price must be valid number greater than 0");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkSellP(input) {
    var sp = input.value;
    //if the sp is null:
    if (!sp) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(sp)) || (sp < 0)) {
            input.setCustomValidity("Sell Price must be valid number greater than 0");
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkSID(input) {
    var s = input.value;
    var sid = Number(input.value);
    //if the sp is null:
    if (!s) {
        input.setCustomValidity('');
    } else {
//check if supplier id is integer:
        if ((typeof sid === 'number') && ((sid % 1) === 0)) {
//make sure Supplier id is 0 or greater:
            if (sid >= 0 && sid <= (Math.pow(2, 31) - 1)) {
                input.setCustomValidity('');
            } else {
                input.setCustomValidity("Please enter a valid supplier ID between 1 and 2,147,483,647");
            }
        } else {
            input.setCustomValidity('Please enter a valid supplier ID between 1 and 2,147,483,647');
        }
    }
}

window.onload = function () { // After all the contents has loaded
//    var qtyCells = document.getElementsByClassName("qtyCells");
//    for(var i=0;i<qtyCells.length;i++){  //iterate through each of them
//       //check if content is more than 0
//       if(parseFloat(qtyCells[i].textContent || qtyCells[i].innerText)>=0){ 
//           qtyCells[i].style.backgroundColor="red"; //change background to red
//       }
//    }
//get the quantity and min threshold column cells 
    var qtyCells = document.getElementsByClassName("qtyCells");
    var minTCells = document.getElementsByClassName("minTCells");
    //loop through them (if the qtyCells is same length as minTCells):
    if (qtyCells.length === minTCells.length) {
        for (var i = 0; i < qtyCells.length; i++) {
            //if the quantity is less than the min Threshold:
            if (parseInt(qtyCells[i].textContent) < parseInt(minTCells[i].textContent)) {
                qtyCells[i].style.backgroundColor="red"; //change to red background color
            }
            
        }
    }
};

