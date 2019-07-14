<?php
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
//$query="select * from currentorders";
//$results=mysqli_query($con,$query);

?>
<!--<br><br><br><br>-->

<h3 class="text-center">Manage Current Orders</h3>
<!--
<div class="container-fluid">
<ul class="nav nav-tabs">
<li><a href="admin_manage_menu.php">Add Menu Item</a></li>
<li class="active"><a href="admin_update_menu.php">Update, Delete Menu Item</a></li>
</ul>
</div>
-->
<br />
<br />
<?php 

if(isset($_SESSION['current_comp']) && ($_SESSION['current_comp']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>Order processed Successfully!</strong>
</div>

<?php
    unset($_SESSION['current_comp']);
}
else if(isset($_SESSION['current_comp']) && ($_SESSION['current_comp']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! Order not processed successfully</strong>
</div>

<?php
    unset($_SESSION['current_comp']);
}

?>

<?php 
if(isset($_SESSION['current_ncomp']) && ($_SESSION['current_ncomp']=="true"))
{ ?>

<div class="alert alert-success">
    <strong>Order Cancelled Successfully!</strong>
</div>

<?php
    unset($_SESSION['current_ncomp']);
}
else if(isset($_SESSION['current_ncomp']) && ($_SESSION['current_ncomp']=="false"))
{ ?>
<div class="alert alert-danger">
    <strong>Error! Order not cancelled.</strong>
</div>

<?php
    unset($_SESSION['current_ncomp']);
}

?>

<?php
$query1="select distinct email from currentorders";
$res=mysqli_query($con,$query1);
?>

<div class="container">
    <div class="table-responsive">

        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>E-Mail</th>
                    <th>Items</th>
                    <th>Total Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <?php
                    while($row=mysqli_fetch_assoc($res))
                    {
                    ?><td><?php echo $row['email'];?></td><?php
                        $query2="select * from currentorders where email='".$row['email']."'";
                        $results=mysqli_query($con,$query2);
                        $row1=mysqli_fetch_assoc($results);
                        $constr=$row1['itemname'];
                        $total=intval($row1['itemprice']);
                        while($row1=mysqli_fetch_assoc($results))
                        {
                            $constr=$constr.",".$row1['itemname'];
                            $total=$total+intval($row1['itemprice']);

                        }
                    ?>

                    <td><?php echo $constr; ?></td>
                    <td><?php echo $total; ?></td>
                    <td>
                        <button class="btn btn-success"><a style="color:white;" href="admin_current_orders_completed.php?email=<?php echo $row['email']; ?>"><span class="glyphicon glyphicon-ok"></span> Completed</a></button>
                        <button class="btn btn-danger"><a style="color:white;" href="admin_current_orders_cancelled.php?email=<?php echo $row['email']; ?>"><span class="glyphicon glyphicon-remove"></span> Cancelled&nbsp;&nbsp;</a></button>
                    </td>
                </tr>
                <?php
                    }
                ?>
            </tbody>
        </table>
    </div>
</div>
<?php
include 'footer.php';
?>