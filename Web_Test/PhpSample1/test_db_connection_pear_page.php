<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252">
<title>PHP sample 1</title>
</head>
<body>

	<table border="1">
		<tr>
			<th colspan="4">Student list</th>
		</tr>

		<tr>
			<th>Name</th>
			<th>Age</th>
			<th>Class</th>
			<th>Score</th>
		</tr>

		<?php
require_once 'test_db_info.php';
require_once 'DB.php';

$connectionString = "mysql://$db_username:$db_password@$db_host/$db_schema";
echo "Connection string = \"$connectionString\".\n<br>";

$connection = DB::connect($connectionString);

if (!DB::isError($connection))
{
	echo "Connection = \"$connection\".\n<br>";
}
else
{
	die("Could not connect to DB: \"" . DB::errorMessage($connection) . "\".<br>");
}

$query = 'SELECT * FROM student ORDER BY class, name';

echo "Query = $query\n<br>";

$resultSet = $connection->query($query);

if (DB::isError($resultSet))
{
	die("Could not query DB: \"" . DB::errorMessage($connection) . "\".<br>");
}

echo "Result set number = {$resultSet->numRows()}\n<br>";

while ($result_row = $resultSet->fetchRow())
{
	echo "<tr>\n";
	echo "<td>{$result_row[0]}</td>\n";
	echo "<td>{$result_row[1]}</td>\n";
	echo "<td>{$result_row[2]}</td>\n";
	echo "<td>{$result_row[3]}</td>\n";
	echo "</tr>\n";
}

$connection->disconnect();

		?>
	</table>
</body>
</html>
