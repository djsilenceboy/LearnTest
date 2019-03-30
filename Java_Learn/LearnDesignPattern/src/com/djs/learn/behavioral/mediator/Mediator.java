
package com.djs.learn.behavioral.mediator;

import java.util.Map;
import java.util.TreeMap;

public class Mediator implements MediatorInterface
{
	int idCount = 0;
	Map<Integer, ColleagueInterface> colleagues = new TreeMap<Integer, ColleagueInterface>();

	public Mediator(){
		System.out.println("Create mediator.");
	}

	@Override
	public void registerColleague(ColleagueInterface colleague){
		colleague.setId(idCount);
		colleague.setMediator(this);
		colleagues.put(idCount, colleague);

		System.out.println("Register colleague <" + idCount + ">.");

		idCount++;
	}

	@Override
	public void sendMessage(int fromId, int toId, String message){
		ColleagueInterface colleague = colleagues.get(new Integer(toId));

		if (colleague == null) {
			System.out.println("Send message from colleague <" + fromId + "> to <Unknown>");
		} else {
			System.out.println("Send message from colleague <" + fromId + "> to <" + toId + ">: " + message);

			colleague.receiveMessage(fromId, message);
		}
	}
}
