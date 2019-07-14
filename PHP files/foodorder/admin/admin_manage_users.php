<?php
include 'header.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
?>
<!--<br><br><br><br>-->
<?php if(isset($_SESSION['added_user']) && ($_SESSION['added_user']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>User Details Added Successfully!</strong>
</div>

<?php
    unset($_SESSION['added_user']);
}
else if(isset($_SESSION['added_user']) && ($_SESSION['added_user']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! User Details Not Added.</strong>
</div>

<?php
    unset($_SESSION['added_user']);
}

?>


<h3 class="text-center">Manage Customers</h3>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li class="active"><a href="admin_manage_users.php">Add Customers</a></li>
        <li><a href="admin_update_users.php">Update, Delete Customers</a></li>

    </ul>
</div>
<br />


<?php if(isset($_SESSION['added_user']) && ($_SESSION['added_user']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>Menu Item Details Updated Successfully!</strong>
</div>

<?php
    unset($_SESSION['added_menu']);
}
else if(isset($_SESSION['added_menu']) && ($_SESSION['added_menu']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! Menu Item Details Not Updated.</strong>
</div>

<?php
    unset($_SESSION['added_menu']);
}

?>



<div class="container">



    <div class="panel panel-primary">
        <div class="panel-heading">Add Customer</div>
        <div class="panel-body">
            <br />
            <form action="admin_manage_users_action.php" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="name" class="form-control" name="name">
                </div>
                <div class="form-group">
                    <label for="username">Email address:</label>
                    <input type="email" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <label for="phone">Phone No:</label>
                    <input type="text" class="form-control" name="phone">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <div class="form-group">
                    <label for="dob">Date Of Birth:</label>
                    <input type="text" class="form-control" name="dob">
                </div>

                <br />
                <br />
                <div class="text-center">
                    <button type="submit" class="btn btn-success" style="align:center;"> &nbsp;&nbsp; Submit <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;&nbsp;
                    </button>
                </div>
            </form>
        </div>
    </div>

</div>

<?php
include 'footer.php';
?>