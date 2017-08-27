<?php
# require_once('test_class.php');
include_once('test_class_cat.php');

$fluffy = new Cat(1);
$fluffy->eat();

Cat::eat();
Cat::birthday();
?>
