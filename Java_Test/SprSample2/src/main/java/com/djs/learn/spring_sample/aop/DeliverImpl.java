
package com.djs.learn.spring_sample.aop;

import java.util.Date;

import org.apache.log4j.Logger;

public class DeliverImpl implements DeliverInterface
{
	private final Logger log = Logger.getLogger(DeliverImpl.class);

	private AirMail airMail;

	public DeliverImpl(){
		log.trace("Enter...");
	}

	public AirMail getAirMail(){
		return airMail;
	}

	public void setAirMail(AirMail airMail){
		airMail.setTransactionId(System.currentTimeMillis());
		this.airMail = airMail;

		log.trace("AirMail = " + airMail);
	}

	@Override
	public String deliver() throws Exception{
		log.debug("Enter 1...");

		String date;

		log.trace("AirMail = " + airMail);

		date = new Date().getTime() + " / " + new Date().toString();

		log.trace("Transaction date = " + date);

		log.debug("Leave 1...");

		return date;
	}

	@Override
	public String deliver(AirMail airMail) throws Exception{
		log.debug("Enter 2...");

		String date;

		log.trace("AirMail = " + airMail);

		date = new Date().getTime() + " / " + new Date().toString();

		log.trace("Transaction date = " + date);

		log.debug("Leave 2...");

		return date;
	}
}
