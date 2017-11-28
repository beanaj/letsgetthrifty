/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkISBN(input) {
    input.setCustomValidity('');
}

function checkGenre(input) {
    var genre = input.value;
    //if the name is null:
    if (!genre) {
        input.setCustomValidity('');
    } else {
        if (genre.length<=1||genre.length>45) {
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
        if (author.length<=1||author.length>45) {
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
        if (title.length<=1||title.length>45) {
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

//NEED TO CHECK IF INTEGER!!!!!
function checkEdition(input) { 
    var edition = input.value;
    //if the name is null:
    if (!edition) {
        input.setCustomValidity('');
    } else {
        if (isNaN(edition)) {
            input.setCustomValidity('Please enter a valid number');
        } else {
            input.setCustomValidity('');
        }
    }
}

function checkPublisher(input) {
    var pub = input.value;
    //if the name is null:
    if (!pub) {
        input.setCustomValidity('');
    } else {
        if (pub.length<=1||pub.length>45) {
            input.setCustomValidity("Publisher name must be between 2 and 45 characters");
        } else {
            input.setCustomValidity('');
        }
    }
}

//CHECK IF INTEGER!!
function checkPubYear(input) {
    var pubYear = input.value;
    //if the pubYear is null:
    if (!pubYear) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(pubYear)) || (pubYear < 0) || (pubYear > 2300)) {
            input.setCustomValidity("Publication Year must be valid year");
        } else {
            input.setCustomValidity('');
        }
    }
}

//CHECK IF INTEGER!!
function checkQuantity(input) {
    var quantity = input.value;
    //if the quantity is null:
    if (!quantity) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(quantity)) || (quantity < 0)) {
            input.setCustomValidity("Quantity must be valid number greater than 0");
        } else {
            input.setCustomValidity('');
        }
    }
}

//CHECK IF INTEGER!!
function checkThreshold(input) {
    var thresh = input.value;
    //if the quantity is null:
    if (!thresh) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(thresh)) || (thresh < 0)) {
            input.setCustomValidity("Min threshold must be valid number greater than 0");
        } else {
            input.setCustomValidity('');
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

//Check if INT!!!!!
function checkSID(input) {
    var s = input.value;
    //if the sp is null:
    if (!s) {
        input.setCustomValidity('');
    } else {
        if ((isNaN(s)) || (s < 0)) {
            input.setCustomValidity("Supplier ID Must be valid number");
        } else {
            input.setCustomValidity('');
        }
    }
}

