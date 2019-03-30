
package com.djs.learn.behavioral.mediator;

public interface ColleagueInterface
{
	void setId(int id);

	int getId();

	void setMediator(MediatorInterface mediator);

	void sendMessage(int toId, String message);

	void receiveMessage(int fromId, String message);
}
