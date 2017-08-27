<html>
<head>
<title>Build a form</title>
</head>
<body>
<?php
$self = $_SERVER["PHP_SELF"];

echo "self = $self<br>";

$search = $_GET["search"];

if ($search == NULL)
{
	echo ('
		<form action="' . $self
			. '" method="get">
			<label>Search:<input type="text" name="search" size="5" maxlength="4" /></label><br>
			<label>Suggestion: <textarea name="suggestions" cols="20" rows="4"></textarea></label><br>
			<fieldset>
			<label>Italian <input type="checkbox" name="food[]" value="Italian" /></label>
			<label>Mexican <input type="checkbox" name="food[]" value="Mexican" checked="checked" /></label>
			<label>Chinese <input type="checkbox" name="food[]" value="Chinese" /></label><br>
			</fieldset>
			<fieldset>
			<label>Italian <input type="radio" name="food2" value="Italian" /></label>
			<label>Mexican <input type="radio" name="food2" value="Mexican" /></label>
			<label>Chinese <input type="radio" name="food2" value="Chinese" checked="checked" /></label><br>
			</fieldset>
			<input type="submit" value="Go!" />
		</form>
	');
}
else
{
	$suggestions = $_GET["suggestions"];
	$food = $_GET["food"];
	$food2 = $_GET["food2"];

	echo "search = <strong>$search</strong><br>";
	echo "suggestions = <strong>$suggestions</strong><br>";
	foreach ($food as $oneFood)
	{
		echo "food = <strong>$oneFood</strong><br>";
	}
	echo "food2 = <strong>$food2</strong><br>";
}
?>
</body>
</html>
