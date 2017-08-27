<?php

function Cat()
{
}

$funcName = "Cat";
$test = function_exists($funcName);
echo "Function \"$funcName\" exist = ".($test?"TRUE":"FALSE").".\n";

$funcName = "Dog";
$test = function_exists($funcName);
echo "Function \"$funcName\" exist = ".($test?"TRUE":"FALSE").".\n";

$sampleString = "This is a sample string.";
$signature = md5($sampleString);
echo "\"$sampleString\" signature is \"$signature\".";
?>
