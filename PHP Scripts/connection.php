<?php
error_reporting(0);
$db_name = "messlogin";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";
mysql_connect($server_name,$mysql_username,$mysql_password) or die(mysql_error());
mysql_select_db($db_name)or die(mysql_error());

?>