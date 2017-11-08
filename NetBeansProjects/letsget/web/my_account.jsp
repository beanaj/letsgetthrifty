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
    
    <div>
        <div id="menu">
            
          <p>Welcome, User!</p>
 
        
        </div>        
        <div id="attribute">
            
            <p>Name</p>
            <p>Email</p>
            <p>Phone</p>
            <p>Address</p>
            <p>Payment</p>
            <p>Password</p>
            <p>Promotions</p>     
            <p>Orders</p>
            
        </div>        
        <div id="edit">
            <div>
                <form class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text" class="pure-input-1" placeholder="First Name" name="firstName">
                        <input type="text" class="pure-input-1" placeholder="Last Name" name="firstName">
                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Update</button>
                    </fieldset>
                </form>
            </div>
            <div>
                <form class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text" class="pure-input-1" placeholder="Email Adress" name="email">
                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Update</button>
                    </fieldset>
                </form>
            </div>
            <div>
                <form class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text" class="pure-input-1" placeholder="Phone Number" name="phone">
                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Update</button>
                    </fieldset>
                </form>
            </div>
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
            <div>
                <form class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text" class="pure-input-1" placeholder="Card Number" name = "card" onchange="checkCard(this)" required>
                        <input id = "exp" type="text" class="pure-input-1" placeholder="Expiration" name = "exp" onchange="checkExp(this)" required>
                        <select id="type" class="pure-input-1" name = "type" required>
                            <option disabled selected value>Select Card Type</option>
                            <option>Visa</option>
                            <option>MasterCard</option>
                            <option>American Express</option>
                            <option>Discover</option>
                            <option>Capital One</option>
                            </select>                        
                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Update</button>
                    </fieldset>
                </form>
            </div>
            <div>
                <form class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text" class="pure-input-1" placeholder="Old Password" name="oldPass">
                        <input type="text" class="pure-input-1" placeholder="New Password" name="newPass">               
                        <input type="text" class="pure-input-1" placeholder="Verify New Password" name="newPassVerify">
                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Update</button>
                    </fieldset>
                </form>
            </div>         
        </div>
    </div>
    
  </body>
</html>