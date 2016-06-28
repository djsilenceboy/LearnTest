
package dj.test.structural.flyweight;

import java.util.Map;
import java.util.TreeMap;

public class ObjectCache
{
	Map<String, SharedObject> sharedObjects = new TreeMap<String, SharedObject>();

	public SharedObject getSharedObject(String name){
		SharedObject sharedObject = sharedObjects.get(name);

		System.out.println("Find SharedObject: " + (sharedObject == null ? "null" : sharedObject.getName()));

		if (sharedObject == null) {
			sharedObject = new SharedObject(name);
			sharedObjects.put(name, sharedObject);
		}

		System.out.println("Return SharedObject: " + sharedObject.getName());

		return sharedObject;
	}
}
