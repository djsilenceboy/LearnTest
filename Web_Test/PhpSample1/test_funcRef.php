<?php
function outer($func, $params) {
	echo "-- outer begin --\n";
	$func($params);
	echo "-- outer end --\n";
}

function inner($names) {
	echo "-- inner begin --\n";
	$i = 0;
	foreach ($names as $name) {
		echo "Name[$i] = $name\n";
		$i++;
	}
	echo "-- inner end --\n";
}

echo outer("inner", array ("Tom", "Jerry", "Mary"));
?>
