<?php
if (isset($_COOKIE['username']))
{
	echo ("Username = " . $_COOKIE['username']);
}
else
{
	echo "Cookie is not set.";
}
?>
