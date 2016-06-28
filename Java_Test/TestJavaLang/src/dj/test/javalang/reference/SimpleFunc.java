
package dj.test.javalang.reference;

public class SimpleFunc
{
	public void setInfo(String info, SimpleObject so){
		so.setInfo(info);
	}

	public void getInfo(String info, SimpleObject so){
		so = new SimpleObject();
		so.setInfo(info);
	}
}
