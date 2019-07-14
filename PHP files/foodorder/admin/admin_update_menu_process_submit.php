<?php
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}

$itemid=$_REQUEST['itemid'];
$itemname=$_REQUEST['itemname'];
$itemprice=$_REQUEST['itemprice'];
$query="update menu set itemname='$itemname',itemprice=$itemprice where itemid=$itemid";
$results=mysqli_query($con,$query);

if($results)
{
    $_SESSION['updated_menu']="true";
}
else
{
    $_SESSION['updated_menu']="false";
}
//header("Location: admin_update_menu.php");
redirect("admin_update_menu.php");
?>