function car(name, seats, haveRadio)
{
	this.name = name;
	this.seats = seats;
	this.haveRadio = haveRadio;
	this.showInfo = showInfo;

	this.showInfo();
}

function showInfo()
{
	document.write("Car name: " + this.name + "; Seats: " + this.seats + "; Have radio: " + (this.haveRadio ? "Yes" : "No") + ".<br>");
}

car();

//

var carA = new car("Aqq", 4, true);
var carB = new car("Ben", 2, false);

document.write("I have a " + carA.name + " car with " + carA.seats + " seats.<br>");
carA.showInfo();
document.write("I have another " + carB.name + " car with " + carB.seats + " seats and " + (carB.haveRadio ? "" : "no ") + "radio.<br>");
carB.showInfo();

//

var carC = new car();

document.write("I have a new " + carC.name + " car with " + carC.seats + " seats.<br>");

carC = {name:"Cad", seats:6, haveRadio:true};
carC.showInfo = showInfo; // If not add this line, the following "carC.showInfo();" will not work.

document.write("I have a new " + carC.name + " car with " + carC.seats + " seats.<br>");
carC.showInfo();

/*
Car name: undefined; Seats: undefined; Have radio: No.
Car name: Aqq; Seats: 4; Have radio: Yes.
Car name: Ben; Seats: 2; Have radio: No.
I have a Aqq car with 4 seats.
Car name: Aqq; Seats: 4; Have radio: Yes.
I have another Ben car with 2 seats and no radio.
Car name: Ben; Seats: 2; Have radio: No.
Car name: undefined; Seats: undefined; Have radio: No.
I have a new undefined car with undefined seats.
I have a new Cad car with 6 seats.
Car name: Cad; Seats: 6; Have radio: Yes.
*/
