
package dj.test.behavioral.chainOfResponsibility;

public interface HandlerTypeAInterface
{
	String getName();

	void setNextHandler(HandlerTypeAInterface nextHandler);

	String process(String content);
}
