<?php
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$query="select * from orderhistory";
$results=mysqli_query($con,$query);

?>

<h3 class="text-center">All Orders</h3>
<br />
<br />
<div class="container">
    <div class="table-responsive">

        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>OrderId</th>
                    <th>E-Mail</th>
                    <th>Description</th>
                    <th>Total</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <?php
                while($row=mysqli_fetch_assoc($results))
                {
                ?>
                <tr>
                    <td><?php echo $row['orderid']; ?></td>
                    <td><?php echo $row['email']; ?></td>
                    <td><?php echo $row['description']; ?></td>
                    <td><?php echo $row['total']; ?></td>
                    <td><?php echo $row['status']; ?></td>
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