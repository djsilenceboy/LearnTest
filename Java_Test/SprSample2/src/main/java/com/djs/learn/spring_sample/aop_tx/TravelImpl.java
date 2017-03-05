
package com.djs.learn.spring_sample.aop_tx;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelImpl implements TravelInterface, TravelInfoLogInterface
{
	private final Logger log = Logger.getLogger(TravelImpl.class);

	@Autowired
	private TravelInfoDo travelInfoDo;

	private String city;

	public TravelImpl(){
		log.trace("Enter...");
	}

	@Override
	public void travel() throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Enter...");
			log.debug("Travle to city: " + city);
		}

		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}

		if (log.isDebugEnabled()) {
			log.debug("Leave...");
		}
	}

	@Override
	public TravelInfoDo getTravelInfoDo(){
		return travelInfoDo;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}
}
