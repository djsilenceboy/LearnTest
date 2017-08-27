<?php
# setcookie("username", "John");
# setcookie("username", "John", time() - 1);
setcookie("username", "John", time() + 10);

echo "Cookie is set.";
?>
