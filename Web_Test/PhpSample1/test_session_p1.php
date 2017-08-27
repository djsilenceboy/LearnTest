<?php
session_start();
$_SESSION['hello'] = 'Hello World';
echo "Page1: session name: " . $_SESSION['hello'];
?>
