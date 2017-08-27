<?php
require_once 'test_db_info.php';

$connection = mysql_connect($db_host, $db_username, $db_password);

if ($connection)
{
	echo "Connection = \"$connection\".\n";
}
else
{
	die("Could not connect to DB: \"" . mysql_error() . "\".");
}

$db_select = mysql_select_db($db_schema);

if ($db_select)
{
	echo "Schema = \"$db_select\".\n";
}
else
{
	die("Could not select schema: \"" . mysql_error() . "\".");
}

$query = 'SELECT * FROM student ORDER BY class, name';

echo "Query = $query\n";

$resultSet = mysql_query($query);

if (!$resultSet)
{
	die("Could not query DB: \"" . mysql_error() . "\".");
}

echo "Result set = \"$resultSet\".\n";

/*
 $i = 0;
 while ($result_row = mysql_fetch_row($resultSet))
 {
 echo "Row [$i] = ";
 echo var_dump($result_row);
 echo "\n";
 $i++;
 }
 */
$i = 0;
while ($result_row = mysql_fetch_array($resultSet, MYSQL_ASSOC))
{
	echo "Row [$i] = ";
	echo var_dump($result_row);
	echo "\n";
	$i++;
}

mysql_close($connection);

?>
