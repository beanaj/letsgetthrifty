<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>My Account</title>
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
    <!--<![endif]-->
    <link rel="stylesheet" href="styles/my_account.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<script src="scripts/login_registerscript.js"></script>-->
    <style media="screen" type="text/css">
        
    </style>
  </head>
  <body>
    <div class="banner">
        <h1 class="bannerhead">
            <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
        </h1>
    </div>
    
      
      <div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="#">Welcome!</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="homepage.jsp" class="pure-menu-link">Home</a></li>
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">My Cart</a></li>
                <li class="pure-menu-item"><a href="search.jsp" class="pure-menu-link">Search</a></li>

                <li class="pure-menu-item menu-item-divided pure-menu-selected">
                    <a href="my_account.jsp" class="pure-menu-link">My Account</a>
                </li>
            </ul>
            <a class="pure-menu-heading" href="#">My Account</a>
            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="my_accountName.jsp" class="pure-menu-link">Name</a></li>
                <li class="pure-menu-item"><a href="my_accountEmail.jsp" class="pure-menu-link">Email</a></li>
                <li class="pure-menu-item"><a href="my_accountPhone.jsp" class="pure-menu-link">Phone</a></li>
                  
                <li class="pure-menu-item menu-item-divided pure-menu-selected">
                    <a href="my_accountAddress.jsp" class="pure-menu-link">Address</a>
                </li>
                                
                <li class="pure-menu-item"><a href="my_accountPayment.jsp" class="pure-menu-link">Payment</a></li>
                <li class="pure-menu-item"><a href="my_accountPassword.jsp" class="pure-menu-link">Password</a></li>
                <li class="pure-menu-item"><a href="my_accountPromotion.jsp" class="pure-menu-link">Promotion</a></li>                
                <li class="pure-menu-item"><a href="my_accountOrders.jsp" class="pure-menu-link">Orders</a></li>
            </ul>
        </div>
    </div>

    <div id="main">

            <div>
                <form class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text" class="pure-input-1" placeholder="Street" name = "street" required>
                        <input type="text" class="pure-input-1" placeholder="City" name = "city" required>
                        <select id="select" class="pure-input-1" name= "state" required>
                                        <option disabled selected value>Select State</option>
                                        <option value="AL">Alabama</option>
                                        <option value="AK">Alaska</option>
                                        <option value="AZ">Arizona</option>
                                        <option value="AR">Arkansas</option>
                                        <option value="CA">California</option>
                                        <option value="CO">Colorado</option>
                                        <option value="CT">Connecticut</option>
                                        <option value="DE">Delaware</option>
                                        <option value="DC">District Of Columbia</option>
                                        <option value="FL">Florida</option>
                                        <option value="GA">Georgia</option>
                                        <option value="HI">Hawaii</option>
                                        <option value="ID">Idaho</option>
                                        <option value="IL">Illinois</option>
                                        <option value="IN">Indiana</option>
                                        <option value="IA">Iowa</option>
                                        <option value="KS">Kansas</option>
                                        <option value="KY">Kentucky</option>
                                        <option value="LA">Louisiana</option>
                                        <option value="ME">Maine</option>
                                        <option value="MD">Maryland</option>
                                        <option value="MA">Massachusetts</option>
                                        <option value="MI">Michigan</option>
                                        <option value="MN">Minnesota</option>
                                        <option value="MS">Mississippi</option>
                                        <option value="MO">Missouri</option>
                                        <option value="MT">Montana</option>
                                        <option value="NE">Nebraska</option>
                                        <option value="NV">Nevada</option>
                                        <option value="NH">New Hampshire</option>
                                        <option value="NJ">New Jersey</option>
                                        <option value="NM">New Mexico</option>
                                        <option value="NY">New York</option>
                                        <option value="NC">North Carolina</option>
                                        <option value="ND">North Dakota</option>
                                        <option value="OH">Ohio</option>
                                        <option value="OK">Oklahoma</option>
                                        <option value="OR">Oregon</option>
                                        <option value="PA">Pennsylvania</option>
                                        <option value="RI">Rhode Island</option>
                                        <option value="SC">South Carolina</option>
                                        <option value="SD">South Dakota</option>
                                        <option value="TN">Tennessee</option>
                                        <option value="TX">Texas</option>
                                        <option value="UT">Utah</option>
                                        <option value="VT">Vermont</option>
                                        <option value="VA">Virginia</option>
                                        <option value="WA">Washington</option>
                                        <option value="WV">West Virginia</option>
                                        <option value="WI">Wisconsin</option>
                                        <option value="WY">Wyoming</option>
                                    </select>
                        <input id = "zip" type="text" class="pure-input-1" placeholder="Zipcode" name = "zip" onchange="checkZip(this)" required>
                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Update</button>
                    </fieldset>
                </form>
            </div>

    </div>
</div>

      <script src="scripts/my_account.js"></script>
      
  </body>
</html>