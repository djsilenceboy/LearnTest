<?php
echo "Number is set: " . (isset($num) ? "TRUE" : "FALSE") . ".\n";

$num = 1;

echo "Number is set: " . (isset($num) ? "TRUE" : "FALSE") . ".\n";

$num2 = &$num;
echo "Number is $num.\n";
echo "Number2 is $num2.\n";

$num2 = 3;
echo "Number is $num.\n";
echo "Number2 is $num2.\n";

$sample = "A sample is <i>italics</i>";
echo htmlentities($sample);
?>
