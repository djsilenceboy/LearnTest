var nameCount = 0;
var names = new Array();

function sortNames() {
	var name = document.theForm.newName.value;
	names[nameCount] = name;
	nameCount++;
	names.sort();
	document.theForm.sortedNames.value = names.join("\n");
}

var numberCount = 0;
var numbers = new Array();

function numberComparator(a, b) {
	return a - b;
}

function sortNumbers() {
	var number = document.theForm.newNumber.value;
	numbers[numberCount] = parseInt(number);
	numberCount++;
	numbers.sort(numberComparator);
	document.theForm.sortedNumbers.value = numbers.join("\n");
}
