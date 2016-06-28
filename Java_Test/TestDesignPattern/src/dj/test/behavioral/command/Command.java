
package dj.test.behavioral.command;

public class Command implements Cloneable
{
	public CommandType commandType;
	public String content;

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

	public CommandType getCommandType(){
		return commandType;
	}

	public void setCommandType(CommandType commandType){
		this.commandType = commandType;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	@Override
	public String toString(){
		return "Command [commandType=" + commandType + ", content=" + content + "]";
	}
}
