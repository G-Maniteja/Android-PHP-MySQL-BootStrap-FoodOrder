<?php

include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$query="select * from menu";
$results=mysqli_query($con,$query);

?>
<!--<br><br><br><br>-->

<h3 class="text-center">Manage Restaurant Menu</h3>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li><a href="admin_manage_menu.php">Add Menu Item</a></li>
        <li class="active"><a href="admin_update_menu.php">Update, Delete Menu Item</a></li>
    </ul>
</div>
<br />
<br />
<?php 

if(isset($_SESSION['updated_menu']) && ($_SESSION['updated_menu']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>Menu Item Details Updated Successfully!</strong>
</div>

<?php
    unset($_SESSION['updated_menu']);
}
else if(isset($_SESSION['updated_menu']) && ($_SESSION['updated_menu']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! Menu Item Details Not Updated.</strong>
</div>

<?php
    unset($_SESSION['updated_menu']);
}

?>

<?php 
if(isset($_SESSION['deleted_menu']) && ($_SESSION['deleted_menu']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>Menu Item Deleted Successfully!</strong>
</div>

<?php
    unset($_SESSION['deleted_menu']);
}
else if(isset($_SESSION['deleted_menu']) && ($_SESSION['deleted_menu']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! Menu Item Not Deleted.</strong>
</div>

<?php
    unset($_SESSION['deleted_menu']);
}

?>


<div class="container">
    <div class="table-responsive">

    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Item Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <?php
            while($row=mysqli_fetch_assoc($results))
            {
            ?>
            <tr>
                <td><?php echo $row['itemname'] ?></td>
                <td><?php echo $row['itemprice'] ?></td>
                <td>
                    <button class="btn btn-success"><a style="color:white;" href="admin_update_menu_process.php?itemid=<?php echo $row['itemid']; ?>"><span class="glyphicon glyphicon-pencil	
                        "></span> Update</a></button>
                    <button class="btn btn-danger"><a style="color:white;" href="admin_delete_menu_process.php?itemid=<?php echo $row['itemid']; ?>"><span class="glyphicon glyphicon-trash	
                        "></span> Delete</a></button>
                </td>
            </tr>
            <?php
            }
            ?>
        </tbody>
    </table>
    </div>
</div>
<?php
include 'footer.php';
?>