<?php
include 'connection.php';
$email=$_REQUEST['email'];
$q="select * from cart where email='$email'";
$res=mysqli_query($con, $q);
$array=array();
$i=0;
while ($row=mysqli_fetch_assoc($res)) {
    $array[$i]=array();
    $array[$i]['itemname']=$row['itemname'];
    $array[$i]['itemprice']=$row['itemprice'];
    $array[$i]['quantity']=$row['quantity'];
    $array[$i]['orderid']=$row['orderid'];
    $array[$i]['qty']=$row['quantity'];
    $i=$i+1;
}
echo json_encode($array);
