<?php
error_reporting(0);
$uname=$_POST['Uname'];
$pass=$_POST['Pass'];
mysql_connect("localhost","root","") or die(mysql_error());
mysql_select_db("messlogin") or die(mysql_error());
$res=mysql_query("select * from logindetails where username='".$uname."' and password='".$pass."'")or die(mysql_error());
if ($row=mysql_fetch_array($res)) {
	echo "Success";	
}else
echo "Fail";
?>

