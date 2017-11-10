/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Addison Powers
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
    
    addBook = function() {
        var new_isbn=document.getElementById("new_isbn").value;
        var new_genre=document.getElementById("new_genre").value;
        var new_author=document.getElementById("new_author").value; 
        var new_title=document.getElementById("new_title").value;
        var new_rating=document.getElementById("new_rating").value;
        document.write(new_isbn + "\n" + new_genre);
        var new_publisher=document.getElementById("new_publisher").value; 
        var new_publicationyear=document.getElementById("new_publicationyear").value;
        var new_quantity=document.getElementById("new_quantity").value;
        var new_buyprice=document.getElementById("new_buyprice").value; 
        var table = document.getElementById("bookTable");
        var table_len=(table.rows.length)-1;
        var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'>\n\
                                                            <td id='isbn_row"+table_len+"'>"+new_isbn+"</td>\n\
                                                            <td id='genre_row"+table_len+"'>"+new_genre+"</td>\n\
                                                            <td id='author_row"+table_len+"'>"+new_author+"</td>\n\
\n\                                                         <td id='title_row"+table_len+"'>"+new_title+"</td>\n\
                                                            <td id='rating_row"+table_len+"'>"+new_rating+"</td>\n\
                                                            <td id='publisher_row"+table_len+"'>"+new_publisher+"</td>\n\
\n\                                                         <td id='publicationyear_row"+table_len+"'>"+new_publicationyear+"</td>\n\
                                                            <td id='quantity_row"+table_len+"'>"+new_quantity+"</td>\n\
                                                            <td id='buyprice_row"+table_len+"'>"+new_buyprice+"</td>\n\
\n\                                                         <td><a href="delete.jsp?deleteid='new_isbn'">Delete</a></td>\n\
                                                        </tr>";
        document.getElementById("new_isbn").value="";
        document.getElementById("new_genre").value="";
        document.getElementById("new_author").value="";
        document.getElementById("new_title").value="";
        document.getElementById("new_rating").value="";
        document.getElementById("new_publisher").value="";
        document.getElementById("new_publicationyear").value="";
        document.getElementById("new_quantity").value="";
        document.getElementById("new_buyprice").value="";
    }

}(this, this.document));



