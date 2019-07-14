<?php
include 'connection.php';
    $email=$_REQUEST['email'];
    $itemname=$_REQUEST['itemname'];
    $itemprice=$_REQUEST['itemprice'];
    $qty=$_REQUEST['qty'];
    $q="insert into cart (email,itemname,itemprice,quantity,status) values('$email','$itemname',$itemprice,$qty,'new')";
    $res=mysqli_query($con,$q);
    if($res>0)
    {
        echo "insert succesfull";
    }
    



?>