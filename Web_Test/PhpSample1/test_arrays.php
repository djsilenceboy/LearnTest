<?php
$months = array( 'Jan', 'Feb', 'Mar' );

extract($months, EXTR_PREFIX_ALL, "months");

echo "months_0 = $months_0\n";
echo "months_1 = $months_1\n";
echo "months_2 = $months_2\n";


$weekdays = array( 'Monday' => 'Dog', 'Tuesday' => 'Cat', 'Wednesday' => 'Pig' );

$weekdaysMore = array( 'Monday' => array( 'Monday' => 'Dog', 'Tuesday' => 'Cat', 'Wednesday' => 'Pig' ), 'Tuesday' => array( 'Monday' => 'Dog', 'Tuesday' => 'Cat', 'Wednesday' => 'Pig' ), 'Wednesday' => array( 'Monday' => 'Dog', 'Tuesday' => 'Cat', 'Wednesday' => 'Pig' ) );

echo "weekdaysMore['Monday']['Monday'] = {$weekdaysMore['Monday']['Monday']}.\n";

foreach ($weekdays as $key => $value)
{
	echo "$key = $value.\n";
}

foreach ($weekdaysMore as $key => $value)
{
	echo "$key = $value.\n";

	foreach ( $value as $key2 => $value2)
	{
		echo "$key2 = $value2.\n";
	}
}

echo var_dump($weekdaysMore);

extract($weekdaysMore);

echo var_dump($Monday);

extract($weekdaysMore, EXTR_PREFIX_ALL, "wkdMore");

echo var_dump($wkdMore_Monday);
?>
