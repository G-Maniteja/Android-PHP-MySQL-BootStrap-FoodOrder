<?php
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$query="select * from users";
$results=mysqli_query($con,$query);

?>
<h3 class="text-center">Manage Customers</h3>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li><a href="admin_manage_users.php">Add Customers</a></li>
        <li class="active"><a href="admin_update_users.php">Update, Delete Customers</a></li>
    </ul>
</div>

<br />
<br />
<?php 
if(isset($_SESSION['updated_user']) && ($_SESSION['updated_user']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>User Details Updated Successfully!</strong>
</div>

<?php
    unset($_SESSION['updated_user']);
}
else if(isset($_SESSION['updated_user']) && ($_SESSION['updated_user']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! User Details Not Updated</strong>
</div>

<?php
    unset($_SESSION['updated_user']);
}

?>

<?php 
if(isset($_SESSION['deleted_user']) && ($_SESSION['deleted_user']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>User Deleted Successfully!</strong>
</div>

<?php
    unset($_SESSION['deleted_user']);
}
else if(isset($_SESSION['deleted_user']) && ($_SESSION['deleted_user']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! User Not Deleted.</strong>
</div>

<?php
    unset($_SESSION['deleted_user']);
}

?>


<div class="container">
    <div class="table-responsive">

        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Password</th>
                    <th>DOB</th>
                    <th>E-Mail</th>
                    <th>Phone No</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <?php
                while($row=mysqli_fetch_assoc($results))
                {
                ?>
                <tr>
                    <td><?php echo $row['username'] ?></td>
                    <td><?php echo $row['password'] ?></td>
                    <td><?php echo $row['dob'] ?></td>
                    <td><?php echo $row['email'] ?></td>
                    <td><?php echo $row['phone'] ?></td>
                    <td>
                        <button class="btn btn-success"><a style="color:white;" href="admin_update_process.php?userid=<?php echo $row['userid']; ?>"><span class="glyphicon glyphicon-pencil	
                            "></span> Update</a></button>
                        <button class="btn btn-danger"><a style="color:white;" href="admin_delete_process.php?userid=<?php echo $row['userid']; ?>"><span class="glyphicon glyphicon-trash	
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