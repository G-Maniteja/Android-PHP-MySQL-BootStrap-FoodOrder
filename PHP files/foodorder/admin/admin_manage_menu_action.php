<?php
include 'header.php';
include 'connection.php';

if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$itemname=$_REQUEST['itemname'];
$itemprice=$_REQUEST['itemprice'];


$query="insert into menu(itemname,itemprice) values('$itemname',$itemprice)";
$results=mysqli_query($con,$query);

?>
<h3 class="text-center">Manage Customers</h3>
<div class="container-fluid">
<ul class="nav nav-tabs">
  <li class="active"><a href="admin_manage_menu.php">Add Menu Item</a></li>
  <li><a href="admin_update_menu.php">Update, Delete Menu Item</a></li>
</ul>
</div>
<?php
    if($results)
    {
        $_SESSION['added_menu']="true";
     }
   else
   {
       $_SESSION['added_menu']="false";
        } 

//header('Location: admin_manage_menu.php');
redirect("admin_manage_menu.php");
?>

<?php
include 'footer.php';
?>