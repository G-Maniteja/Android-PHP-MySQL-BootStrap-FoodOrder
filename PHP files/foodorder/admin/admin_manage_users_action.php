<?php
include 'header.php';
include 'connection.php';

if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$name=$_REQUEST['name'];
$email=$_REQUEST['username'];
$phone=$_REQUEST['phone'];
$password=$_REQUEST['password'];
$dob=$_REQUEST['dob'];



$query="insert into users(username,password,dob,email,phone) values('$name','$password','$dob','$email','$phone')";
$results=mysqli_query($con,$query);

?>
<h3 class="text-center">Manage Customers</h3>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li class="active"><a href="admin_manage_users.php">Add Customers</a></li>
        <li><a href="admin_update_users.php">Update, Delete Customers</a></li>
    </ul>
</div>
<?php
if($results)
{
    $_SESSION['added_user']="true";
}
else
{
    $_SESSION['added_user']="false";
}

//header('Location: admin_manage_users.php');
redirect("admin_manage_users.php");

include 'footer.php';
?>