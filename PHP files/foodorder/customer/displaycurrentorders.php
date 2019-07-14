<?php
include 'connection.php';
$email=$_REQUEST['email'];
$query="select * from currentorders where email='".$email."'";
$result=mysqli_query($con,$query);
$array=array();
$i=0;
while($row=mysqli_fetch_assoc($result))
{
$array[$i]=array();
   $array[$i]['itemname']=$row['itemname'];
   $array[$i]['itemprice']=$row['itemprice'];
   $array[$i]['orderid']=$row['orderid'];
   $array[$i]['qty']=$row['quantity'];
   $i=$i+1;
}
echo json_encode($array);

?>