<?php
$timestamp = time();
echo date("Y/m/d G:i:sa O e T", $timestamp);
echo "<br>";
echo date("c", $timestamp);
echo "<br>";
echo date("r", $timestamp);
echo "<br>";

echo ("Validating: 4/31/2005<br>");
if (checkdate(4, 31, 2005))
{
	echo ('Date accepted.');
}
else
{
	echo ('Invalid date.');
}
echo ("<br>");
echo "<br>";

echo ("Validating: 5/31/2005<br>");
if (checkdate(5, 31, 2005))
{
	echo ('Date accepted.');
}
else
{
	echo ('Invalid date.');
}
echo "<br>";
echo "<br>";

$new_date = mktime(18, 05, 35, 5, 31, 2005);
echo date("r", $new_date);
echo "<br>";

?>
