<?php
require_once 'test_db_info.php';
require_once 'DB.php';

if (!isset($_SERVER['PHP_AUTH_USER']) || !isset($_SERVER['PHP_AUTH_PW']))
{
	header('WWW-Authenticate: Basic realm="Member Area"');
	header("HTTP/1.0 401 Unauthorized");
	echo "You must enter in a username and password combination!";
	exit;
}

$web_username = $_SERVER['PHP_AUTH_USER'];
$web_password = $_SERVER['PHP_AUTH_PW'];

$connectionString = "mysql://$db_username:$db_password@$db_host/$db_schema";
echo "Connection string = \"$connectionString\".\n<br>";
$connection = DB::connect($connectionString);

if (DB::isError($connection))
{
	die("Could not connect to DB: \"" . DB::errorMessage($connection) . "\".");
}

$query = "SELECT * FROM login_user WHERE username='" . $web_username . "' AND password=MD5('" . $web_password . "') LIMIT 1";
echo "Query = $query\n<br>";
$result = $connection->query($query);

if (DB::isError($result))
{
	die("Could not query DB: \"" . DB::errorMessage($connection) . "\".");
}

$row = $result->fetchRow(DB_FETCHMODE_ASSOC);
if ($row == null)
{
	header('WWW-Authenticate: Basic realm="Member Area"');
	header("HTTP/1.0 401 Unauthorized");
	echo "Your username and password combination was incorrect!";
	exit;
}
else
{
	echo "You entered a username of \"" . $_SERVER['PHP_AUTH_USER'] . "\"";
	echo " and a password of \"" . $_SERVER['PHP_AUTH_PW'] . "\".";
}
?>
