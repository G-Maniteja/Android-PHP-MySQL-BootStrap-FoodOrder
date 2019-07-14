<?php
include 'header.php';
include 'connection.php';
//$query1="select distinct email from currentorders ";
//$res=mysqli_query($con,$query1);

//while($row=mysqli_fetch_assoc($res))
//
$e=$_REQUEST['email'];
$query2="select * from currentorders where email='".$e."'";
$results=mysqli_query($con,$query2);
$row1=mysqli_fetch_assoc($results);
$constr=$row1['itemname'];
$total=intval($row1['itemprice']);
while($row1=mysqli_fetch_assoc($results))
{
    $constr=$constr.",".$row1['itemname'];
    $total=$total+intval($row1['itemprice']);
}

$query2="insert into orderhistory(email,description,total,status) values('".$e."','".$constr."',$total,'Completed')";
$res1=mysqli_query($con,$query2);
if($res1)
{
    $query3="delete from currentorders where email='".$e."'";
    $res2=mysqli_query($con,$query3);
    if($res2)
    {
        $_SESSION['current_comp']='true';  
    }
    else
    {
        $_SESSION['current_comp']='false'; 
    }


}
else
{
    $_SESSION['current_comp']='false';
}
//header('Location: admin_current_orders.php');
redirect("admin_current_orders.php");




?>