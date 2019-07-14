<?php include 'header.php';?>
    <p> </p>
    <div class="container">
        <?php if(isset($_SESSION['login_flag']))
        {?>
            <div class="alert alert-danger">
              <strong>Invalid Login!</strong> Please enter the correct login credintials.
            </div>
        
        <?php 
        unset($_SESSION['login_flag']);
        } ?>
        
        
        <?php if(isset($_SESSION['logout']))
        {?>
            <div class="alert alert-success">
              <strong>Logout Successful!</strong> 
            </div>
        
        <?php 
        unset($_SESSION['logout']);
        } ?>
        
        
        <h2 class="text-center">Admin Login</h2>
        <br />
        <br />
        <form action="login.php" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading">Login</div>
                <div class="panel-body">
                            <br />
                            <div class="form-group">
                                <label for="username">Email address:</label>
                                <input type="email" class="form-control" name="username">
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" class="form-control" name="password">
                            </div>
                            <div class="form-group form-check">
                                <label class="form-check-label">
                                <input class="form-check-input" type="checkbox"> Remember me
                                </label>
                            </div>
                            <br />
                            <br />
                            <div class="text-center">
                                <button type="submit" class="btn btn-success" style="align:center;"> &nbsp;&nbsp; Submit <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;&nbsp;
                                </button>
                            </div>
                        </div>
                </div>
        </form>

    </div>




    <?php
    include 'footer.php';
    ?>