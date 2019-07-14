<?php
include "connection.php";
$username = $_REQUEST['username'];
   $password = $_REQUEST['password'];
   $result = mysqli_query($con,"SELECT * FROM users where email='$username' 
      and password='$password'");
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   if(mysqli_num_rows($result)){
      echo "true";
   }
    else
    {
        echo "false";
    }
   
?>