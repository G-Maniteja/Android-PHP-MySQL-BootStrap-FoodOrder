<?php
include 'connection.php';

$q1="select * from orderhistory";
    $res=mysqli_query($con,$q1);
    $content="";
    while($row=mysqli_fetch_assoc($res))
    {
        $content=$content.$row['description']."\n";
    }
    //echo $content."uiubh";
    $filename="dataset1.txt";
    file_put_contents($filename, $content);
    


?>