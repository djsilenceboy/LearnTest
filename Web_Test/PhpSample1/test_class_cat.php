<?php
class Cat
{
	var $age = 0;

	function Cat($age)
	{
		echo "Cat::Cat().\n";

		$this->age = $age;

		echo "Cat::Cat(): age = $this->age.\n";
	}

	function getAge(&$age)
	{
		$age = $this->age;
	}

	function meow()
	{
		echo "Cat::meow().\n";
	}

	function eat()
	{
		echo "Cat::eat().\n";

		if ($this == null)
		{
			echo "No such Cat.\n";
		}
		else
		{
			echo "One such Cat.\n";
		}
	}

	function birthday()
	{
		echo "Cat::birthday().\n";

		$this->age++;

		echo "Cat::birthday(): age = $this->age.\n";
	}
}

class BigCat extends Cat
{
	function BigCat()
	{
		echo "BigCat::BigCat().\n";

		parent::Cat(5);
	}

	function eat()
	{
		echo "BigCat::eat().\n";

		parent::eat();
		$this->meow();
	}
}

$fluffy = new Cat(1);
echo "Fluffy is a new \"".gettype($fluffy)."\"!\n";

$age = -1;
$fluffy->getAge($age);
echo "Fluffy's age is $age.\n";

$fluffy->birthday();
$fluffy->getAge($age);
echo "Fluffy's age is $age.\n";

$fluffy->eat();

$bigCat = new BigCat();

$bigCat->getAge($age);
echo "bigCat's age is $age.\n";

$bigCat->eat();
?>
