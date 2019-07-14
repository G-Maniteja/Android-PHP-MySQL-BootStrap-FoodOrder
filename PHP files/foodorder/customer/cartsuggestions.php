<?php
include 'class.apriori.php';
include 'connection.php';

$q1="select * from orderhistory";
$res=mysqli_query($con,$q1);
$content="";
while($row=mysqli_fetch_assoc($res))
{
    $content=$content.$row['description']."\n";
}
$filename="dataset1.txt";
file_put_contents($filename, $content);




$Apriori = new Apriori();

$Apriori->setMaxScan(20);       //Scan 2, 3, ...
$Apriori->setMinSup(2);         //Minimum support 1, 2, 3, ...
$Apriori->setMinConf(75);       //Minimum confidence - Percent 1, 2, ..., 100
$Apriori->setDelimiter(',');    //Delimiter 

$Apriori->process('dataset1.txt');
$content1=$Apriori->saveAssociationRules('associationRules.txt');
//print_r($content1);
$a1=array();
$a1=explode("\n",$content1);
//$a1[count($a1)]="Idly Sambar,Masala Dosa=Vada Sambar,Masala Dosa";
//print_r($a1);


$email=$_REQUEST['email'];
$q="select * from cart where email='$email'";
$res=mysqli_query($con,$q);
$array=array();
$c=0;
while($row=mysqli_fetch_assoc($res))
{
    $array[$c++]=$row["itemname"];
}

$count=count($a1);

$array1=array();
$i1=0;

for($i=0;$i<$count;$i=$i+1)
{
    $a2=array();
    $a2=explode("=",$a1[$i]);

    $larray=array();
    $rarray=array();

    $larray=explode(",",$a2[0]);
    $rarray=explode(",",$a2[1]);
    $flag=0;
    for($j=0;$j<count($larray);$j=$j+1)
    {
        for($k=0;$k<count($array);$k=$k+1)
        {
            if($larray[$j]==$array[$k])
            {
                $flag=$flag+1;
            }
        }
    }
    if($flag==count($larray))
    {

        for($j=0;$j<count($rarray);$j=$j+1)
        {
            $q2="select * from menu";
            $res1=mysqli_query($con,$q2);
            while($row=mysqli_fetch_assoc($res1))
            {
                if($rarray[$j]==$row['itemname'])
                {
                    $array1[$i1]=array();
                    $array1[$i1]['itemname']=$row['itemname'];
                    $array1[$i1]['itemprice']=$row['itemprice'];
                    $array1[$i1]['itemid']=$row['itemid'];
                    $i1=$i1+1;
                }
            }
        }


    }



}


echo json_encode($array1);
?>