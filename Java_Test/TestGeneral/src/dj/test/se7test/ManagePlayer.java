
package dj.test.se7test;

public class ManagePlayer
{
	public static void main(String[] args){
		Player p1 = new Archer();
		// System.out.format("Player 1 %s their %s.", p1.attack(), p1.weapon);
		// Cannot access p1.weapon.
	}
}

class Player
{
	private String weapon = "Fist";

	String attack(){
		return "Attacks with";
	}
}

class Archer extends Player
{
	String weapon = "Arrow";

	@Override
	String attack(){
		return "Shoots";
	}
}
