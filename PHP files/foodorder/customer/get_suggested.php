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

//Frequent Itemsets
//echo '<h1>Frequent Itemsets</h1>';
//$Apriori->printFreqItemsets();

//       echo '<h3>Frequent Itemsets Array</h3>';
//        print_r($Apriori->getFreqItemsets()); 
//
//        //Association Rules
//        //echo '<h1>Association Rules</h1>';
//        //$Apriori->printAssociationRules();
//
//        echo '<h3>Association Rules Array</h3>';
//        print_r($Apriori->getAssociationRules()); 

//Save to file
$arr=$Apriori->saveFreqItemsets('freqItemsets.txt');
$Apriori->saveAssociationRules('associationRules.txt');

// print_r($arr);
//for($i=0;$i<count($arr);$i++)
//{
//    echo "$arr[$i]"."<br>";
//}




$itemids=array();
$itemids_pointer=0;

$itemnames=array();
$itemnames_pointer=0;

$itemprice=array();
$itemprice_pointer=0;


for($p1=0;$p1<count($arr);$p1++)
{
    $arr1=explode(",",$arr[$p1]);
    $temp=array();
    $j=0;
    $itemnames_str="";
    $itemprice_int=0;
    
    for($i=0;$i<count($arr1);$i++)
    {
        $q2="select * from menu";
        $res1=mysqli_query($con,$q2);
        while($row=mysqli_fetch_assoc($res1))
        {
            if($row['itemname']==$arr1[$i])
            {
                $temp[$j++]=$row['itemid'];
                $itemnames_str=$itemnames_str.$row['itemname'].",";
                $itemprice_int=$itemprice_int+(int)$row['itemprice'];
                break;
            }
        }
    }
    $itemids[$itemids_pointer++]=$temp;
    $itemnames[$itemnames_pointer++]=$itemnames_str;
    $itemprice[$itemprice_pointer++]=$itemprice_int;

}
//print_r($itemids);
//print_r($itemnames);
//print_r($itemprice);

$array=array();
for($i=0;$i<count($itemnames);$i++)
{
   $array[$i]=array();
   $array[$i]['itemname']=$itemnames[$i];
   $array[$i]['itemprice']=$itemprice[$i];
  
}
echo json_encode($array);






?> 

<?php

//$filename="freqitemsets.txt";
//$content=file($filename);
//echo print_r($content);
//$i=0;
//$size=count($content);
//while($i<=$size)
//{
//    echo $content[$i];
//
//    $i=$i+1;
//}
?>
