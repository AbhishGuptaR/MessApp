<?php
error_reporting(0);
$uname=$_POST['Uname'];
$pass=$_POST['Pass'];
mysql_connect("localhost","root","") or die(mysql_error());
mysql_select_db("messlogin") or die(mysql_error());
$res=mysql_query("insert into logindetails values ('$uname','$pass')")or die(mysql_error());
if($res)
{
	echo "Success";
}
else
echo "Fail";
?>

