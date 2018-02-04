
package com.djs.learn.spring_sample.aop_tx;

import java.util.Date;

public class TravelInfoDo
{
	private Date start;
	private Date stop;

	public Date getStart(){
		return start;
	}

	public void setStart(Date start){
		this.start = start;
	}

	public Date getStop(){
		return stop;
	}

	public void setStop(Date stop){
		this.stop = stop;
	}

	@Override
	public String toString(){
		return "TravelInfoDo [start=" + start + ", stop=" + stop + "]";
	}
}
