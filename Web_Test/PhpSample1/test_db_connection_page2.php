<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252">
<title>PHP sample 2</title>
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

$connection = mysqli_connect($db_host, $db_username, $db_password, $db_schema);

if (mysqli_connect_errno()) {
	die("Connection failed: ".mysqli_connect_error()."<br>");
}

echo "Connected.<br>";

$sql = 'SELECT * FROM student ORDER BY class, name';

echo "SQL = $sql\n<br>";

$result = mysqli_query($connection, $sql);

if (!$result) {
	die("Query failed.<br>");
}

echo "Rows = ".mysqli_num_rows($result).".<br>";

if (mysqli_num_rows($result) > 0) {
	while($row = mysqli_fetch_assoc($result)) {
		echo "<tr>\n";
		echo "<td>{$row['name']}</td>\n";
		echo "<td>{$row['age']}</td>\n";
		echo "<td>{$row['class']}</td>\n";
		echo "<td>{$row['score']}</td>\n";
		echo "</tr>\n";
	}

	mysqli_free_result($result);
}

mysqli_close($connection);

		?>
	</table>
</body>
</html>
