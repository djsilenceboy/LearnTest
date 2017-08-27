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

$connection = mysql_connect($db_host, $db_username, $db_password);

if ($connection)
{
	echo "Connection = \"$connection\"\n.<br>";
}
else
{
	die("Could not connect to DB: \"" . mysql_error() . "\".<br>");
}

$db_select = mysql_select_db($db_schema, $connection);

if ($db_select) {
	echo "Schema = \"$db_select\"\n.<br>";
} else {
	die("Could not select schema: \"" . mysql_error() . "\".<br>");
}

$sql = 'SELECT * FROM student ORDER BY name';

echo "SQL = $sql\n<br>";

$result = mysql_query($sql, $connection);

if (!$result)
{
	die("Could not query DB: \"" . mysql_error() . "\".<br>");
}

echo "Result = \"$result\".\n<br>";

while ($row = mysql_fetch_assoc($result))
{
	echo "<tr>\n";
	echo "<td>{$row['name']}</td>\n";
	echo "<td>{$row['age']}</td>\n";
	echo "<td>{$row['class']}</td>\n";
	echo "<td>{$row['score']}</td>\n";
	echo "</tr>\n";
}

mysql_free_result($result);

mysql_close($connection);

		?>
	</table>
</body>
</html>
