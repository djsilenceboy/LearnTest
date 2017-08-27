<?php
require_once 'PEAR.php';

if (get_magic_quotes_gpc())
{
	echo "Magic quotes are enabled.";
}
else
{
	echo "Magic quotes are disabled.";
}
echo "\n";

$sql = "INSERT INTO book VALUES ('ok', \"yes\")";
echo "SQL1 = $sql";
echo "\n";

if (get_magic_quotes_gpc())
{ //guard against SQL injection
	$sql = stripslashes($qstring);
	echo "SQL2 = $sql";
	echo "\n";
}

$sql = mysql_real_escape_string($sql);
# $sql = addslashes($sql);
echo "SQL3 = $sql";
echo "\n";

?>
