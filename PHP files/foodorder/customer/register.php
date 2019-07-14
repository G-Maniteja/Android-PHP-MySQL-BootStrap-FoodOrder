<?php
    include 'connection.php';
    $username=$_REQUEST['username'];
    $email=$_REQUEST['email'];
    $password=$_REQUEST['password'];
    $phone=$_REQUEST['phone'];
    $dob=$_REQUEST['dob'];
    $q="insert into users (username,email,password,phone,dob) values('$username','$email','$password','$phone','$dob')";
    $res=mysqli_query($con,$q);
    if(mysqli_num_rows($res)>0)
    {
        echo "successful";
    }
    else
    {
        echo "unsuccessful";
    }

?>