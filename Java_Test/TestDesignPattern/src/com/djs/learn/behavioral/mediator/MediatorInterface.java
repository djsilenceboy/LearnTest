
package com.djs.learn.behavioral.mediator;

public interface MediatorInterface
{
	void registerColleague(ColleagueInterface colleague);

	void sendMessage(int fromId, int toId, String message);
}
