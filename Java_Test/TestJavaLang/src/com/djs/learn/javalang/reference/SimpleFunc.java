
package com.djs.learn.javalang.reference;

public class SimpleFunc
{
	public void setInfo(String info, SimpleObject simpleObj){
		simpleObj.setInfo(info);
	}

	public void getInfo(String info, SimpleObject simpleObj){
		simpleObj = new SimpleObject();
		simpleObj.setInfo(info);
	}
}
