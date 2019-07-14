<?php 
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}

$userid=$_REQUEST['userid'];
$q1="delete from users where userid=$userid";
$res=mysqli_query($con,$q1);
if($res)
{
    $_SESSION['deleted_user']="true";
}
else
{
    $_SESSION['deleted_user']="false";
}
//header("Location: admin_update_users.php");
redirect("admin_update_users.php");
?>