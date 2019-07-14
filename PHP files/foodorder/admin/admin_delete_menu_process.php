<?php 
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
                  
$itemid=$_REQUEST['itemid'];
$q1="delete from menu where itemid=$itemid";
$res=mysqli_query($con,$q1);
if($res)
{
    $_SESSION['deleted_menu']="true";
}
else
{
    $_SESSION['deleted_menu']="false";
}
//header("Location: admin_update_menu.php");
redirect("admin_update_menu.php");
?>