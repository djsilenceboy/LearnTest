
package dj.test.behavioral.chainOfResponsibility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class HandlerTypeBController
{
	TreeMap<Integer, HandlerTypeBInterface> handlers = new TreeMap<Integer, HandlerTypeBInterface>();

	public void registerHandler(Integer id, HandlerTypeBInterface handler){
		System.out.println("Register handler: " + handler.getName());

		handlers.put(id, handler);
	}

	public String process(String content){
		Set<Integer> keys = handlers.keySet();

		for (Integer key : keys) {
			HandlerTypeBInterface handler = handlers.get(key);

			content = handler.processInput(content);
		}

		List<Integer> reverseKeys = new ArrayList<Integer>(keys);
		Collections.reverse(reverseKeys);

		for (Integer key : reverseKeys) {
			HandlerTypeBInterface handler = handlers.get(key);

			content = handler.processOutput(content);
		}

		return content;
	}
}
