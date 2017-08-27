<?php
$value = 42;

printf("%d<br>", $value);
printf("%b<br>", $value);
printf("%c<br>", $value);
printf("%f<br>", $value);
printf("%o<br>", $value);
printf("%s<br>", $value);
printf("%x<br>", $value);
printf("%X<br>", $value);

echo "<br>";

printf("Space padding can be tricky in HTML %5d.<br>", $value);
printf("<pre>Space padding can be tricky in HTML %5d.</pre>", $value);

echo "<br>";

$total = sprintf("Please pay $%.2f.<br>", 42.4242);
echo $total;
echo "<br>";

$username = "John Doe";
echo ("$username in uppercase is " . strtoupper($username) . ".<br>");
echo ("$username in lowercase is " . strtolower($username) . ".<br>");
echo ("$username in first letter uppercase is " . ucwords($username) . ".<br>");
echo "<br>";
?>
