<?php
require_once 'test_db_info.php';
require_once 'DB.php';

$connectionString = "mysql://$db_username:$db_password@$db_host/$db_schema";
echo "Connection string = \"$connectionString\".\n";

$connection = DB::connect($connectionString);

if (!DB::isError($connection))
{
	echo "Connection = \"$connection\".\n";
}
else
{
	die("Could not connect to DB: \"" . DB::errorMessage($connection) . "\".");
}

$query = 'SELECT * FROM student ORDER BY class, name';

echo "Query = $query\n";

$resultSet = $connection->query($query);

if (DB::isError($resultSet))
{
	die("Could not query DB: \"" . DB::errorMessage($connection) . "\".");
}

echo "Result set number = {$resultSet->numRows()}\n";

$i = 0;
while ($result_row = $resultSet->fetchRow())
{
	echo "Row [$i] = ";
	echo var_dump($result_row);
	echo "\n";
	$i++;
}

$connection->disconnect();

?>
