<%-- 
    Document   : confirmation
    Created on : Nov 4, 2017, 12:56:36 AM
    Author     : andrewjacobsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Email</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/confirmationstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="scripts/login_registerscript.js"></script>
        <style media="screen" type="text/css">

        </style>
    </head>
    <body>
        <div class="banner">
            <h1 class="bannerhead">
                <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
            </h1>
        </div>
        <div class = "pure-g">
            <div class="pure-u-1 pure-u-md-1-3">
            </div>
            <div class="pure-u-1 pure-u-md-1-3">
                <div class="cheader">
                    FORGOT PASSWORD?
                </div>
                <br>
                <div class ="cinput">
                    <h1>Please enter your userID and email below to send a reset email!</h1>
                    <form class="pure-form" method = "post" action = "forgot">
                        <fieldset class="pure-group">
                            <input type="text" class="pure-input-1" placeholder="AccountID" name = "accountID" onchange="checkAccount(this)" required>
                            <input type="text" class="pure-input-1" placeholder="Email" name = "email" required>
                            <br>
                            <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Send Email</button>
                        </fieldset>
                        <div id="error">
                                
                                    ${requestScope.error}
                                
                        </div>
                    </form>                 
                </div>
            </div>
            <div class="pure-u-1 pure-u-md-1-3">
            </div>
        </div>
</html>
