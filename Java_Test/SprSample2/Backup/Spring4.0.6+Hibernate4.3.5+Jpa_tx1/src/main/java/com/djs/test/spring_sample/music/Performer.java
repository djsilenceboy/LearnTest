
package com.djs.learn.spring_sample.music;

public interface Performer
{
	public String getSong();

	public void tuneInstrument();

	public void perform() throws PerformanceException;

	public void cleanInstrument();
}
