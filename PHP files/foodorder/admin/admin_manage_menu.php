<?php
include 'header.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
?>
<!--<br><br><br><br>-->

<?php if(isset($_SESSION['added_menu']) && ($_SESSION['added_menu']=="true"))
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
    

<h3 class="text-center">Manage Restaurant Menu</h3>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li class="active"><a href="admin_manage_menu.php">Add Menu Item</a></li>
        <li><a href="admin_update_menu.php">Update, Delete Menu Item</a></li>

    </ul>
</div>
<br />

<div class="container">
    
    
    
    <div class="panel panel-primary">
        <div class="panel-heading">Add Menu Item</div>
        <div class="panel-body">
            <br />
            <form action="admin_manage_menu_action.php" method="post">
                <div class="form-group">
                    <label for="itemname">Item Name:</label>
                    <input type="text" class="form-control" name="itemname">
                </div>
                <div class="form-group">
                    <label for="itemprice">Item Price:</label>
                    <input type="text" class="form-control" name="itemprice">
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

