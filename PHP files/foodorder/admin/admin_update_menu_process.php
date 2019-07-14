<?php 
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$itemid=$_REQUEST['itemid'];

$query="select * from menu where itemid=$itemid";
$results=mysqli_query($con,$query);
$row=mysqli_fetch_assoc($results);

?>
<!--
<br />
<br />
<br />
<br />
-->
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">Update Menu Item</div>
        <div class="panel-body">
            <br />
            <form action="admin_update_menu_process_submit.php?itemid=<?php echo $itemid; ?>" method="post">
                <div class="form-group">
                    <label for="itemname">Item Name:</label>
                    <input type="text" class="form-control" name="itemname" value="<?php echo $row['itemname']; ?>">
                </div>
                <div class="form-group">
                    <label for="username">Item Price:</label>
                    <input type="text" class="form-control" name="itemprice" value="<?php echo $row['itemprice']; ?>">
                </div>
                

                <br />
                <br />
                <div class="text-center">
                    <button type="submit" class="btn btn-success" style="align:center;"> &nbsp;&nbsp; Submit <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;&nbsp;
                    </button>
                </div>
            </form>
        </div>
</div>
<?php
include 'footer.php';
?>
