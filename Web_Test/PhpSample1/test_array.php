<?php
$weekdaysA[] = 'Monday';
$weekdaysA[] = 'Tuesday';

echo "WeekdaysA[0] = $weekdaysA[0].\n";
echo "WeekdaysA[1] = $weekdaysA[1].\n";

echo var_dump($weekdaysA);

$weekdaysB[1] = 'Monday';
$weekdaysB[3] = 'Tuesday';

echo "WeekdaysB[1] = $weekdaysB[1].\n";
echo "WeekdaysB[3] = $weekdaysB[3].\n";

echo var_dump($weekdaysB);

$weekdaysC = array( 'Monday', 'Tuesday', 'Wednesday', 'Thurday', 'Friday', 'Saturday', 'Sunday' );

echo "WeekdaysC[2] = $weekdaysC[2].\n";
echo "WeekdaysC[4] = $weekdaysC[4].\n";

echo var_dump($weekdaysC);

$nameA['James'] = 'Bond';
$nameA['Mary'] = 'Gold';

echo "nameA['James'] = {$nameA['James']}.\n";
echo "nameA['Mary'] = {$nameA['Mary']}.\n";

foreach ($nameA as $key => $value)
{
	echo "$key = $value.\n";
}

echo var_dump($nameA);

$size = count($nameA);
echo "nameA size = $size.\n";

foreach ($weekdaysC as $key => $value)
{
	echo "$key = $value.\n";
}

sort($weekdaysC);

foreach ($weekdaysC as $key => $value)
{
	echo "$key = $value.\n";
}

echo var_dump($nameA);
?>
