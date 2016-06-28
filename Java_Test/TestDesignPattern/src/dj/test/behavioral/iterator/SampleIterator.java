
package dj.test.behavioral.iterator;

public class SampleIterator
{
	int id;
	SampleAggregate data;
	int currentIndex = 0;
	boolean violated = false;

	public SampleIterator(int id, SampleAggregate data){
		this.id = id;
		this.data = data;

		System.out.println("Create iterator ID: " + id);
	}

	public void setViolated(boolean violated){
		System.out.println("Iterator ID " + id + ": Set violated");
		this.violated = violated;
	}

	public String get(){
		if (!violated) {
			System.out.println("Iterator ID " + id + ": current index: " + currentIndex);
			if (currentIndex < data.size()) return data.get(currentIndex);
			else return null;
		} else return null;
	}

	public void next(){
		if (currentIndex < data.size()) currentIndex++;
		System.out.println("Iterator ID " + id + ": next index: " + currentIndex);
	}
}
