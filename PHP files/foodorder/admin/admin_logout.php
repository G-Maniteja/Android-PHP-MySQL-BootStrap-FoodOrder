<?php
include 'header.php';
if(isset($_SESSION['username']))
{
  unset($_SESSION['username']);
}
$_SESSION['logout']='true';
//header('Location: index.php');
redirect("index.php");

?>


<?php
include 'footer.php';
?>