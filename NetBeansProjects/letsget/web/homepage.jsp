<%-- 
    Document   : homepage
    Created on : Nov 5, 2017, 12:07:53 PM
    Author     : Ian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Let's Get Thrifty</title>
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
    <!--<![endif]-->
    <link rel="stylesheet" href="styles/homepagestyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="scripts/login_registerscript.js"></script>
    <style media="screen" type="text/css">
        .pure-form {
            vertical-align: middle;
            text-align: center;
        }

        h2.list{
            text-align: center;
        }
    </style>
  </head>
  <body>
    <div class="banner">
        <h1 class="bannerhead">
            <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
        </h1>
    </div>
      <form class="pure-form">
          <input type="search" placeholder="Search">
          <button type="submit" class="pure-button pure-button-primary">Search</button>
      </form>
      <h2 class="list">Recent Additions</h2>
      <%for (int i=0; i<40; i++){%>
          <div class="pure-u-1">
              <p>book</p>
      <%}%>
          </div>
  
  </body>
</html>
