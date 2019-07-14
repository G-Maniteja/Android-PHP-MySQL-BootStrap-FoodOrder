<?php
include "connection.php";
$email=$_POST['email'];
$itemname=$_POST['itemname'];
$itemprice=$_POST['itemprice'];
$orderid=$_POST['orderid'];
$query="delete from cart where orderid=".$orderid;
//."and email='".$email."' and itemname='".$itemname."' and itemprice=".$itemprice;
$result=mysqli_query($con,$query);

if($result){
      echo "true";
   }
    else
    {
        echo "false";
    }
?>