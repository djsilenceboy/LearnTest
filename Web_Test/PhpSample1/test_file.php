<?php
$file_name = "test_file.php";

if (file_exists($file_name))
{
	echo ("$file_name does exist.");
}
else
{
	echo ("$file_name does not exist.");
}
echo ("<br>");

if (is_readable($file_name))
{
	echo ("The file $file_name is readable.");
}
else
{
	echo ("The file $file_name is not readable.");
}
echo ("<br>");

if (is_writeable($file_name))
{
	echo ("The file $file_name is writeable.");
}
else
{
	echo ("The file $file_name is not writeable.");
}
echo ("<br>");

//Only works on Windows with PHP 5.0.0 or later
if (is_executable($file_name))
{
	echo ("The file $file_name is executable.");
}
else
{
	echo ("The file $file_name is not executable.");
}
echo ("<br>");

$file_name = "test.txt";

if (file_exists($file_name))
{
	echo ("$file_name does exist.");
}
else
{
	echo ("The file $file_name does not exist.");
	touch($file_name);
}
echo ("<br>");

if (file_exists($file_name))
{
	echo ("The file $file_name does exist.");
	unlink($file_name);
}
else
{
	echo ("The file $file_name does not exist.");
}
echo ("<br>");

if (file_exists($file_name))
{
	echo ("The file $file_name does exist.");
}
else
{
	echo ("The file $file_name does not exist.");
}
echo ("<br>");
?>
