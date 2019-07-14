<?php
//session_start();
include 'header.php';
include "connection.php";
$username = $_REQUEST['username'];
$password = $_REQUEST['password'];
$result = mysqli_query($con,"SELECT * FROM admin where email='$username' and password='$password'");

if(mysqli_num_rows($result))
{
    $_SESSION['username']=$username;
    //header("location:admin_home.php");
    redirect("admin_home.php");
}
else
{
    $_SESSION['login_flag']="true";
   // header("location:index.php");
    redirect("index.php");
}
   
?>