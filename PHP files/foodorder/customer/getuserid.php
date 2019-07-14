<?php
//include 'connection.php';
//$response=array();
//$q="select userid,username from users where email='$_REQUEST['email']'";
//$res=mysqli_query($con,$q);
//while($row=mysqli_fetch_assoc($res))
//{
//    $response['id']=$row['userid'];
//    $response['username']=$row['username'];
//}
//echo json_encode($response);
include 'connection.php';
$response=array();
$email=$_REQUEST['email'];
$q="select userid,username,email,dob,phone from users where email='$email'";
$res=mysqli_query($con,$q);
$row=mysqli_fetch_assoc($res);
    $response['id']=$row['userid'];
    $response['username']=$row['username'];
    $response['dob']=$row['dob'];
    $response['phone']=$row['phone'];

echo json_encode($response);
?>