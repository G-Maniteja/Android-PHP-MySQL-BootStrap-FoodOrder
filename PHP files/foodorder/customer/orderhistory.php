<?php
include 'connection.php';
$email=$_REQUEST['email'];
//$status=$_REQUEST['status'];
$q="select * from orderhistory where email='$email' order by orderid desc";
$res=mysqli_query($con,$q);
$array=array();
$i=0;
while($row=mysqli_fetch_assoc($res))
{
$array[$i]=array();
   $array[$i]['orderid']=$row['orderid'];
   $array[$i]['desc']=$row['description'];
   $array[$i]['total']=$row['total'];
   $array[$i]['status']=$row['status'];
   $i=$i+1;
}
echo json_encode($array);


?>