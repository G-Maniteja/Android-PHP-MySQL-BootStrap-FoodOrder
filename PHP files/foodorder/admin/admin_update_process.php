<?php 
include 'header.php';
include 'connection.php';
if(!isset($_SESSION['username']))
{
    //header("Location: index.php");
    redirect("index.php");
}
$userid=$_REQUEST['userid'];

$query="select * from users where userid=$userid";
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
        <div class="panel-heading">Update Customer</div>
        <div class="panel-body">
            <br />
            <form action="admin_update_process_submit.php?userid=<?php echo $userid; ?>" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="name" class="form-control" name="username" value="<?php echo $row['username']; ?>">
                </div>
                <div class="form-group">
                    <label for="username">Email address:</label>
                    <input type="email" class="form-control" name="email" value="<?php echo $row['email']; ?>">
                </div>
                <div class="form-group">
                    <label for="phone">Phone No:</label>
                    <input type="text" class="form-control" name="phone" value="<?php echo $row['phone']; ?>">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password" value="<?php echo $row['password']; ?>">
                </div>
                <div class="form-group">
                    <label for="dob">Date Of Birth:</label>
                    <input type="text" class="form-control" name="dob" value="<?php echo $row['dob']; ?>">
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
