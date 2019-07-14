<?php  session_start();?>
<html>
    <head>
        <title>FoodOrder</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

    </head>
    <body>

        <!--        <nav class="navbar navbar-expand-sm bg-dark navbar-dark navbar navbar-toggleable-md navbar-light bg-faded">-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">FoodOrder</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="navbarNavDropdown" class="navbar-collapse collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="admin_home.php">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin_manage_users.php">Manage Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin_manage_menu.php">Restaurant Menu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About Us</a>
                    </li>

                </ul>


                <?php
                if(isset($_SESSION['username']))
                { ?>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            <?php echo $_SESSION['username']; ?>
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="admin_logout.php">Logout</a>
                        </div>
                    </li>
                </ul>
                <?php } else{ ?>
                <ul class="nav navbar-nav ">
                    <li class="nav-item"><a class="nav-link" href="index.php">Login</a></li>
                </ul>
                <?php } ?>
            </div>
        </nav>

        <br />
        <br />
        <br />
        <br />
        <div class="container text-center">
            <div align="center">
                <div class="row">

                    <div class="col col-xl-4 col-lg-6 col-md-12 col-sm-12" class style="margin-top:25px;">
                        <div class="card" style="width:250px; height:200px">
                            <img class="card-img-top" src="image1.jpg" alt="">
                            <div class="card-img-overlay" style="color:white;">
                                <strong>
                                    <h4 class="card-title">Manage Users</h4>
                                    <p class="card-text">Add, Delete, Update the customers of the restaurant</p>
                                    <button class="btn btn-outline-warning"><a href="admin_manage_users.php" class="card-link" style="color:white;">Manage Users</a></button>
                                </strong>
                            </div>
                        </div>
                    </div>


                    <div class="col col-xl-4 col-lg-6 col-md-12 col-sm-12" class style="margin-top:25px;">
                        <div class="card" style="width:250px; height:200px" class style="margin-top:25px;">
                            <img class="card-img-top" src="image1.jpg" alt="">
                            <div class="card-img-overlay" style="color:white;">
                                <strong>
                                    <h4 class="card-title">Restaurant Menu</h4>
                                    <p class="card-text">Add, Delete, Update the Menu Items of the restaurant</p>
                                    <button class="btn btn-outline-warning"><a href="admin_manage_menu.php" class="card-link" style="color:white;">Manage Menu</a></button>   
                                </strong>
                            </div>
                        </div>
                    </div>


                    <div class="col col-xl-4 col-lg-6 col-md-12 col-sm-12" class style="margin-top:25px;">
                        <div class="card" style="width:250px; height:200px">
                            <img class="card-img-top" src="image1.jpg" alt="">
                            <div class="card-img-overlay" style="color:white;">
                                <strong>
                                    <h4 class="card-title">Current Orders</h4>
                                    <p class="card-text">Add, Delete, Update the Current Orders of the Restaurant</p>
                                    <button class="btn btn-outline-warning"><a href="admin_current_orders.php" class="card-link" style="color:white;">Manage Current Orders</a></button>
                                </strong>
                            </div>
                        </div>
                    </div>
                    <!--                </div>-->



                    <!--                <div class="row">-->

                    <div class="col col-xl-4 col-lg-6 col-md-12 col-sm-12" class style="margin-top:25px;">
                        <div class="card" style="width:250px; height:200px">
                            <img class="card-img-top" src="image1.jpg" alt="">
                            <div class="card-img-overlay" style="color:white;">
                                <strong>
                                    <h4 class="card-title">Suggested Items</h4>
                                    <p class="card-text">Update the Suggested Items Displayed to the Customers.</p>
                                    <button class="btn btn-outline-warning"><a href="#" class="card-link" style="color:white;">Manage Cart</a></button> 
                                </strong>
                            </div>
                        </div>
                    </div>

                    <div class="col col-xl-4 col-lg-6 col-md-12 col-sm-12" class style="margin-top:25px;">
                        <div class="card" style="width:250px; height:200px">
                            <img class="card-img-top" src="image1.jpg" alt="">
                            <div class="card-img-overlay" style="color:white;">
                                <strong>
                                    <h4 class="card-title">All Orders</h4>
                                    <p class="card-text">Displays all the Orders of Customers of the Restaurant</p>
                                    <button class="btn btn-outline-warning"><a href="admin_all_orders.php" class="card-link" style="color:white;">Manage Orders</a></button>  
                                </strong>
                            </div>
                        </div>
                    </div>



                    <div class="col col-xl-4 col-lg-6 col-md-12 col-sm-12" class style="margin-top:25px;">
                        <div class="card" style="width:250px; height:200px">
                            <img class="card-img-top" src="image1.jpg" alt="">
                            <div class="card-img-overlay" style="color:white;">
                                <strong>
                                    <h4 class="card-title">Manage Offers</h4>
                                    <p class="card-text">Add, Delete, Update the Offers for the Restaurant</p>
                                    <button class="btn btn-outline-warning"><a href="#" class="card-link" style="color:white;">Manage Offers</a></button> 
                                </strong>
                            </div>
                        </div>
                    </div>

                </div>
            </div>



            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
