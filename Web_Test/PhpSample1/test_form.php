<html>
<head>
<title>Build a form</title>
</head>
<body>
	<form action=<?php
echo ($_SERVER['PHP_SELF']);
				 ?> method="get">
		<label>Search:<input type="text" name="search" size="5" maxlength="4" />
		</label><br> <label>Suggestion: <textarea name="suggestions" cols="20"
				rows="4"></textarea> </label><br>
		<fieldset>
			<label>Italian <input type="checkbox" name="food[]" value="Italian" />
			</label> <label>Mexican <input type="checkbox" name="food[]"
				value="Mexican" /> </label> <label>Chinese <input type="checkbox"
				name="food[]" value="Chinese" checked="checked" /> </label>
		</fieldset>
		<br>
		<fieldset>
			<label>Italian <input type="radio" name="food" value="Italian" /> </label>
			<label>Mexican <input type="radio" name="food" value="Mexican" /> </label>
			<label>Chinese <input type="radio" name="food" value="Chinese"
				checked="checked" /> </label>
		</fieldset>
		<br> <input type="submit" value="Go!" />
	</form>
</body>
</html>
