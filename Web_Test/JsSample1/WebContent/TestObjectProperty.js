function car(name, seats, haveRadio)
{
	this.name = name;
	this.seats = seats;
	this.haveRadio = haveRadio;
	this.showCarInfo = showCarInfo;

	this.showCarInfo();
}

function showCarInfo()
{
	document.write("Car name: " + this.name + "; Seats: " + this.seats + "; Have radio: " + (this.haveRadio ? "Yes" : "No") + ".<br>");
}

var carA = new car("Aqq", 4, true);

document.write("External: Car name: " + carA.name + "; Seats: " + carA.seats + "; Have radio: " + (carA.haveRadio ? "Yes" : "No") + ".<br>");

document.write("External: Car name: " + carA["name"] + "; Seats: " + carA["seats"] + "; Have radio: " + (carA["haveRadio"] ? "Yes" : "No") + ".<br>");

carA.showCarInfo();

for (var propName in carA)
	document.write("For-in: Car[" + propName + "] = " + carA[propName] + ".<br>");

with (carA)
{
	document.write("With: Car name: " + name + "; Seats: " + seats + "; Have radio: " + (haveRadio ? "Yes" : "No") + ".<br>");
}

function person(name, age, car)
{
	this.name = name;
	this.age = age;
	this.car = car;
	this.showPersonInfo = showPersonInfo;

	this.showPersonInfo();
}

function showPersonInfo()
{
	document.write("Person name: " + this.name + "; Age: " + this.age + "; ");
	// this.car.showCarInfo();
	with (this.car)
	{
		showCarInfo();
	}
}

var personA = new person("Jack", 24, carA);

personA.showPersonInfo();

/*
Car name: Aqq; Seats: 4; Have radio: Yes.
External: Car name: Aqq; Seats: 4; Have radio: Yes.
External: Car name: Aqq; Seats: 4; Have radio: Yes.
Car name: Aqq; Seats: 4; Have radio: Yes.
For-in: Car[name] = Aqq.
For-in: Car[seats] = 4.
For-in: Car[haveRadio] = true.
For-in: Car[showCarInfo] = function showCarInfo() { document.write("Car name: " + this.name + "; Seats: " + this.seats + "; Have radio: " + (this.haveRadio ? "Yes" : "No") + ".
"); }.
With: Car name: Aqq; Seats: 4; Have radio: Yes.
Person name: Jack; Age: 24; Car name: Aqq; Seats: 4; Have radio: Yes.
Person name: Jack; Age: 24; Car name: Aqq; Seats: 4; Have radio: Yes.
*/
