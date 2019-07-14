<?php
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}

$userid=$_REQUEST['userid'];
$name=$_REQUEST['username'];
$email=$_REQUEST['email'];
$phone=$_REQUEST['phone'];
$password=$_REQUEST['password'];
$dob=$_REQUEST['dob'];




$query="update users set username='$name',email='$email',phone='$phone',password='$password',dob='$dob' where userid=$userid";
$results=mysqli_query($con,$query);

if($results)
{
    
    $_SESSION['updated_user']="true";

}
else
{
    
    $_SESSION['updated_user']="false";

}
?><?php
//header("location:admin_update_users.php");
redirect("admin_update_users.php");
?>