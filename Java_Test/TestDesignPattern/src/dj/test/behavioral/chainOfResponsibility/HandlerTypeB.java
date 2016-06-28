
package dj.test.behavioral.chainOfResponsibility;

public class HandlerTypeB extends AbstractHandlerTypeB
{
	public HandlerTypeB(String name){
		this.name = name;

		System.out.println("Create handler: " + name);
	}

	@Override
	public String processInput(String content){
		System.out.println("Handler <" + name + "> input: " + content);

		content = ((content == null) ? "" : content + "-") + name;

		System.out.println("Handler <" + name + "> processed input: " + content);

		return content;
	}

	@Override
	public String processOutput(String content){
		System.out.println("Handler <" + name + "> ouput: " + content);

		content = "[" + ((content == null) ? "" : content) + "]";

		System.out.println("Handler <" + name + "> processed ouput: " + content);

		return content;
	}
}
