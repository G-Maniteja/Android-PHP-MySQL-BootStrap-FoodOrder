<?php
include 'connection.php';
$str=$_REQUEST['oids'];
$items=array();
$items=explode(",",$str);
$i=0;
while($i<count($items))
{
    //echo $items[$i];
    $s="select * from cart where orderid=".$items[$i];
    $results=mysqli_query($con,$s);
    if($results)
    {
        $row=mysqli_fetch_assoc($results);
        $s1="insert into currentorders (email,itemname,itemprice,quantity) values('".$row['email']."','".$row['itemname']."',".$row['itemprice'].",".$row['quantity'].")";
        
        $res=mysqli_query($con,$s1);
        if($res)
        {
            //echo "success";
            $s2="delete from cart where orderid=".$items[$i]."";
            $r=mysqli_query($con,$s2);
            if($r)
            {
                echo "success";
            }
        }
    }
    $i=$i+1;
}

?>