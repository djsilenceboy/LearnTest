
package dj.test.behavioral.command;

import java.util.ArrayList;

public class CommandDocument
{
	ArrayList<Command> commandList = new ArrayList<Command>();
	ArrayList<String> innerDocument = new ArrayList<String>();

	public void write(String content){
		Command command = new Command();
		command.setCommandType(CommandType.Write);
		command.setContent(content);

		commandList.add(command);

		System.out.println("Add command: " + command);
	}

	public void erase(){
		Command command = new Command();
		command.setCommandType(CommandType.Erase);

		commandList.add(command);

		System.out.println("Add command: " + command);
	}

	public void redo(){
		if (commandList.size() > 0) {
			Command command = commandList.get(commandList.size() - 1);

			commandList.add((Command)command.clone());

			System.out.println("Add command: " + command);
		}
	}

	public void undo(){
		if (commandList.size() > 0) {
			Command command = commandList.remove(commandList.size() - 1);

			System.out.println("Remove command: " + command);
		}
	}

	public StringBuilder getDocument(){
		StringBuilder document = new StringBuilder();

		for (Command command : commandList) {
			if (command.getCommandType() == CommandType.Write) {
				document.append(command.getContent());
				document.append("\n");
			} else {
				int position = document.lastIndexOf("\n");
				if (position > 0) {
					document.setLength(position);
				}
			}
		}

		return document;
	}
}
