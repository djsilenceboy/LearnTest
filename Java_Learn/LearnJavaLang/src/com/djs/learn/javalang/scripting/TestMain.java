
package com.djs.learn.javalang.scripting;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestMain
{
	ScriptEngineManager scriptEngineManager;
	ScriptEngine scriptEngine;

	public void testEngine(){
		try {
			scriptEngineManager = new ScriptEngineManager();
			scriptEngine = scriptEngineManager.getEngineByName("nashorn");

			System.out.println("ScriptEngineManager = " + scriptEngineManager);
			System.out.println("ScriptEngine = " + scriptEngine);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testScript1(){
		Object result;

		try {
			scriptEngine.put("k", 1234);
			result = scriptEngine.eval("k + 1");

			System.out.println("result = " + result);

			System.out.println("--------------------");

			scriptEngine.eval("function greet(how, who) {return how + ',' + who + '!'}");
			result = ((Invocable)scriptEngine).invokeFunction("greet", "Hello", "Tom");

			System.out.println("result = " + result);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testScript2(){
		Object result;

		try {
			scriptEngine.eval("function welcome(who) {return 'Hello, ' + who + '!'}");
			Greeting greeting = ((Invocable)scriptEngine).getInterface(Greeting.class);
			System.out.println("Greeting = " + greeting);

			result = greeting.welcome("Jerry");

			System.out.println("result = " + result);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestMain test = new TestMain();

		System.out.println("========================================");

		test.testEngine();

		System.out.println("========================================");

		test.testScript1();

		System.out.println("========================================");

		test.testScript2();

		System.out.println("========================================");
	}
}
