
package com.djs.learn.behavioral.mediator;

public class Colleague implements ColleagueInterface
{
	int id;
	MediatorInterface mediator;

	public Colleague(){
		System.out.println("Create colleague.");
	}

	@Override
	public void setId(int id){
		System.out.println("Set colleague ID: " + id);
		this.id = id;
	}

	@Override
	public int getId(){
		return id;
	}

	@Override
	public void setMediator(MediatorInterface mediator){
		System.out.println("Colleague <" + id + ">: Set mediator.");
		this.mediator = mediator;
	}

	@Override
	public void sendMessage(int toId, String message){
		System.out.println("Colleague <" + id + ">: Send message to Colleague <" + toId + ">: " + message);
		mediator.sendMessage(id, toId, message);
	}

	@Override
	public void receiveMessage(int fromId, String message){
		System.out.println("Colleague <" + id + ">: Receive message from Colleague <" + fromId + ">: " + message);

		if (!message.equalsIgnoreCase("Ack")) {
			System.out.println("Colleague <" + id + ">: Send ACK message to Colleague <" + fromId + ">");
			mediator.sendMessage(id, fromId, "Ack");
		}
	}
}
