
package dj.test.javalang.annotations;

@WorkInProgress
public class Theater
{
	@Actor(name = "Tom", age = 10)
	private String actor1;

	@Actor(name = "Jerry")
	private String actor2;

	@Music("Song")
	private String music1;

	@Task(name = "Get actor 1")
	public String getActor1(){
		return actor1;
	}

	@WorkInProgress
	public String getActor2(){
		return actor2;
	}

	@WorkInProgress
	@Task(name = "Get actor 3")
	public String getActor3(){
		return actor1;
	}
}
