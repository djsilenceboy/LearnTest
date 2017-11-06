
package com.djs.learn;

import com.djs.learn.behavioral.chainOfResponsibility.HandlerTypeA;
import com.djs.learn.behavioral.chainOfResponsibility.HandlerTypeB;
import com.djs.learn.behavioral.chainOfResponsibility.HandlerTypeBController;
import com.djs.learn.behavioral.command.CommandDocument;
import com.djs.learn.behavioral.interpreter.ContextData;
import com.djs.learn.behavioral.interpreter.InterpreterA;
import com.djs.learn.behavioral.interpreter.InterpreterB;
import com.djs.learn.behavioral.interpreter.InterpreterInterface;
import com.djs.learn.behavioral.iterator.SampleAggregate;
import com.djs.learn.behavioral.iterator.SampleIterator;
import com.djs.learn.behavioral.mediator.Colleague;
import com.djs.learn.behavioral.mediator.ColleagueInterface;
import com.djs.learn.behavioral.mediator.Mediator;
import com.djs.learn.behavioral.mediator.MediatorInterface;
import com.djs.learn.behavioral.memento.Machine;
import com.djs.learn.behavioral.memento.MachineMemento;
import com.djs.learn.behavioral.memento.MementoKeeper;
import com.djs.learn.behavioral.observer.Observer;
import com.djs.learn.behavioral.observer.Subject;
import com.djs.learn.behavioral.state.StateContext;
import com.djs.learn.behavioral.strategy.StrategyA;
import com.djs.learn.behavioral.strategy.StrategyB;
import com.djs.learn.behavioral.strategy.StrategyContext;
import com.djs.learn.behavioral.template.ConcreteA;
import com.djs.learn.behavioral.template.ConcreteB;
import com.djs.learn.behavioral.template.SampleTemplate;
import com.djs.learn.behavioral.visitor.Element;
import com.djs.learn.behavioral.visitor.ElementInterface;
import com.djs.learn.behavioral.visitor.Visitor;
import com.djs.learn.behavioral.visitor.VisitorInterface;
import com.djs.learn.creational.abstractFactory.FactoryInterface;
import com.djs.learn.creational.abstractFactory.ProductTypeAFactory;
import com.djs.learn.creational.abstractFactory.ProductTypeBFactory;
import com.djs.learn.creational.builder.BuilderA;
import com.djs.learn.creational.builder.BuilderB;
import com.djs.learn.creational.builder.BuilderInterface;
import com.djs.learn.creational.builder.Director;
import com.djs.learn.creational.builder.Product;
import com.djs.learn.creational.common.ProductInterface;
import com.djs.learn.creational.common.ProductType;
import com.djs.learn.creational.factory.Factory;
import com.djs.learn.creational.prototype.SampleToy;
import com.djs.learn.creational.singleton.UniqueToy;
import com.djs.learn.creational.singleton.UniqueToyFactory;
import com.djs.learn.structural.adapter.Adaptee;
import com.djs.learn.structural.adapter.Adapter;
import com.djs.learn.structural.adapter.AdapterInterface;
import com.djs.learn.structural.bridge.Bridge;
import com.djs.learn.structural.bridge.BridgeInterface;
import com.djs.learn.structural.bridge.OperaterA;
import com.djs.learn.structural.bridge.OperaterB;
import com.djs.learn.structural.composite.BigToy;
import com.djs.learn.structural.composite.SmallToy;
import com.djs.learn.structural.decorator.DecoratorControl;
import com.djs.learn.structural.decorator.MoreDecoratorControl;
import com.djs.learn.structural.decorator.SimpleControl;
import com.djs.learn.structural.facade.FacadeFactory;
import com.djs.learn.structural.facade.FacadeInterface;
import com.djs.learn.structural.flyweight.ObjectCache;
import com.djs.learn.structural.proxy.ProxySubject;
import com.djs.learn.structural.proxy.SubjectInterface;

public class TestMain
{
	public void testFacotry(){
		System.out.println("Test pattern: Creational / Factory (Virtual Constructor)\n");

		Factory factory = new Factory();
		ProductType[] productTypes = new ProductType[]{ProductType.ProductTypeA, ProductType.ProductTypeB};

		for (ProductType productType : productTypes) {
			ProductInterface product = factory.create(productType);
			System.out.println("Created product: " + product.getType() + " / " + product.getName());
		}
	}

	public void testAbstractedFacotry(){
		System.out.println("Test pattern: Creational / Abstracted Factory (Kit)\n");

		FactoryInterface[] factories = new FactoryInterface[]{new ProductTypeAFactory(), new ProductTypeBFactory()};

		for (FactoryInterface factory : factories) {
			ProductInterface product = factory.create();
			System.out.println("Created product: " + product.getType() + " / " + product.getName());
		}
	}

	public void testBuilder(){
		System.out.println("Test pattern: Creational / Builder\n");

		Director director = new Director();
		BuilderInterface[] builders = new BuilderInterface[]{new BuilderA(), new BuilderB()};

		for (BuilderInterface builder : builders) {
			Product product = director.create(builder);
			System.out.println("Created product: " + product);
		}
	}

	public void testPrototype(){
		System.out.println("Test pattern: Creational / Prototype (Clone)\n");

		SampleToy sampleToyA = new SampleToy();
		sampleToyA.setName("SampleToyA");
		sampleToyA.setType("TypeA");
		System.out.println("Created SampleToyA: " + sampleToyA);

		SampleToy sampleToyB = (SampleToy)sampleToyA.clone();
		System.out.println("Cloned SampleToyB: " + sampleToyB);

		sampleToyB.setName("SampleToyB");
		sampleToyB.setType("TypeB");

		System.out.println("Now SampleToyA: " + sampleToyA);
		System.out.println("Now SampleToyB: " + sampleToyB);
	}

	public void testSingleton(){
		System.out.println("Test pattern: Creational / Singleton\n");

		UniqueToyFactory uniqueToyFactory = new UniqueToyFactory();
		UniqueToy uniqueToyA = uniqueToyFactory.create();
		System.out.println("Created UniqueToyA: " + uniqueToyA);

		UniqueToy uniqueToyB = uniqueToyFactory.create();
		System.out.println("Created UniqueToyB: " + uniqueToyB);

		uniqueToyB.setName("UniqueToyB");
		uniqueToyB.setType("TypeB");

		System.out.println("Now UniqueToyA: " + uniqueToyA);
		System.out.println("Now UniqueToyB: " + uniqueToyB);
	}

	public void testChainOfResponsibility(){
		System.out.println("Test pattern: Behavioral / Chain of Responsibility (Filter, Intercepter)\n");

		HandlerTypeA handlerTypeA1 = new HandlerTypeA("HandlerTypeA1");
		HandlerTypeA handlerTypeA2 = new HandlerTypeA("HandlerTypeA2");
		HandlerTypeA handlerTypeA3 = new HandlerTypeA("HandlerTypeA3");

		handlerTypeA1.setNextHandler(handlerTypeA2);
		handlerTypeA2.setNextHandler(handlerTypeA3);
		handlerTypeA3.setNextHandler(null);

		handlerTypeA1.process("Cat");
		System.out.println("------------------------------");

		{
			HandlerTypeBController handlerTypeBController = new HandlerTypeBController();
			HandlerTypeB handlerTypeB1 = new HandlerTypeB("HandlerTypeB11");
			HandlerTypeB handlerTypeB2 = new HandlerTypeB("HandlerTypeB12");
			HandlerTypeB handlerTypeB3 = new HandlerTypeB("HandlerTypeB13");

			handlerTypeBController.registerHandler(1, handlerTypeB1);
			handlerTypeBController.registerHandler(2, handlerTypeB2);
			handlerTypeBController.registerHandler(4, handlerTypeB3);

			handlerTypeBController.process("Dog");
		}

		System.out.println("------------------------------");

		{
			HandlerTypeBController handlerTypeBController = new HandlerTypeBController();
			HandlerTypeB handlerTypeB1 = new HandlerTypeB("HandlerTypeB21");
			HandlerTypeB handlerTypeB2 = new HandlerTypeB("HandlerTypeB22");
			HandlerTypeB handlerTypeB3 = new HandlerTypeB("HandlerTypeB23");

			handlerTypeBController.registerHandler(1, handlerTypeB1);
			handlerTypeBController.registerHandler(4, handlerTypeB2);
			handlerTypeBController.registerHandler(2, handlerTypeB3);

			handlerTypeBController.process("Dog");
		}
	}

	public void testCommand(){
		System.out.println("Test pattern: Behavioral / Command (Action, Transaction)\n");

		CommandDocument commandDocument = new CommandDocument();

		commandDocument.write("Hello.");
		commandDocument.redo();
		commandDocument.write("World.");
		commandDocument.redo();
		commandDocument.write("Cat.");
		commandDocument.undo();
		commandDocument.write("Dog.");
		System.out.println("------------------------------");

		System.out.println(commandDocument.getDocument());
	}

	public void testInterpreter(){
		System.out.println("Test pattern: Behavioral / Interpreter (Translater)\n");

		ContextData contextData = new ContextData();
		contextData.setContext("Cat");

		System.out.println("Original: " + contextData.getContext());

		InterpreterInterface[] interpreters = new InterpreterInterface[]{new InterpreterA(), new InterpreterB()};

		for (InterpreterInterface interpreter : interpreters) {

			System.out.println("New: " + interpreter.interpreter(contextData));
		}
	}

	public void testIterator(){
		System.out.println("Test pattern: Behavioral / Iterator (Cursor)\n");

		SampleAggregate aggregate = new SampleAggregate();
		aggregate.add("CatA");
		aggregate.add("DogA");
		aggregate.add("CatB");

		SampleIterator iterator1 = aggregate.createIterator();

		System.out.println("Iterator 1: " + iterator1.get());
		System.out.println("Iterator 1: " + iterator1.get());
		iterator1.next();
		System.out.println("Iterator 1: " + iterator1.get());

		aggregate.add("DogB");
		System.out.println("Iterator 1: " + iterator1.get());
		System.out.println("------------------------------");

		SampleIterator iterator2 = aggregate.createIterator();
		System.out.println("Iterator 2: " + iterator2.get());
		iterator2.next();
		System.out.println("Iterator 2: " + iterator2.get());

		aggregate.add("CatC");
	}

	public void testMediator(){
		System.out.println("Test pattern: Behavioral / Mediator (Message Controller/Message bus)\n");

		MediatorInterface mediator = new Mediator();

		ColleagueInterface colleagueA = new Colleague();
		mediator.registerColleague(colleagueA);

		ColleagueInterface colleagueB = new Colleague();
		mediator.registerColleague(colleagueB);

		colleagueA.sendMessage(colleagueB.getId(), "Hello.");

		colleagueA.sendMessage(10, "Hello.");
	}

	public void testMemento(){
		System.out.println("Test pattern: Behavioral / Memento (Token, Snapshot)\n");

		MementoKeeper mementoKeeper = new MementoKeeper();
		Machine machine = new Machine();

		machine.setStates(new String[]{"State A1", "State A2", "State A3"});
		System.out.println("Machine state: " + machine);

		MachineMemento mementoA = machine.createMemento();
		mementoKeeper.addMemento("State A", mementoA);

		machine.setStates(new String[]{"State B1", "State B2"});
		System.out.println("Machine state: " + machine);

		MachineMemento mementoB = machine.createMemento();
		mementoKeeper.addMemento("State B", mementoB);

		machine.restoreMemento(mementoKeeper.getMemento("State A"));
		System.out.println("Machine state: " + machine);
	}

	public void testObserver(){
		System.out.println("Test pattern: Behavioral / Observer (Publishe-Subscribe, Dependents)\n");

		Subject subject = new Subject();

		subject.register(new Observer("Cat"));
		subject.register(new Observer("Dog"));
		subject.register(new Observer("Bird"));

		subject.notify("Rain is coming!");
	}

	public void testState(){
		System.out.println("Test pattern: Behavioral / State (State Machine)\n");

		StateContext stateContext = new StateContext();

		stateContext.operation("Cat");
		stateContext.operation("Dog");
		stateContext.operation("Bird");
		stateContext.operation("Fish");
	}

	public void testStrategy(){
		System.out.println("Test pattern: Behavioral / Strategy (Policy)\n");

		StrategyContext strategyContext = new StrategyContext("Cat", "Dog");

		strategyContext.process();

		strategyContext.setStrategy(new StrategyB());
		strategyContext.process();

		strategyContext.setStrategy(new StrategyA());
		strategyContext.process();
	}

	public void testTemplate(){
		System.out.println("Test pattern: Behavioral / Template (Extend abstract class)\n");

		SampleTemplate[] templates = new SampleTemplate[]{new ConcreteA(), new ConcreteB()};

		for (SampleTemplate template : templates) {
			template.showName();
		}
	}

	public void testVisitor(){
		System.out.println("Test pattern: Behavioral / Visitor (Inverse Of Control)\n");

		VisitorInterface visitor = new Visitor("Dog");
		ElementInterface[] elements = new ElementInterface[]{new Element("Cat"), new Element("Bird")};

		for (ElementInterface element : elements) {
			element.acceptVisitor(visitor);
		}
	}

	public void testAdapter(){
		System.out.println("Test pattern: Structural / Adapter (Wrapper, Wrapper incompatible interfaces, Reuse existing interfaces)\n");

		AdapterInterface adapter = new Adapter(new Adaptee());

		adapter.setName("Tom");
		adapter.setAge(6);
		adapter.process();
	}

	public void testBridge(){
		System.out
		        .println("Test pattern: Structural / Bridge (Handle/Body, Unified interface with different implementaion, Separate interface and implementaion)\n");

		BridgeInterface bridge = new Bridge();

		bridge.setOperater(new OperaterA());
		bridge.process("Cat");
		bridge.setOperater(new OperaterB());
		bridge.process("Cat");
	}

	public void testComposite(){
		System.out.println("Test pattern: Structural / Composite (Whole/Parts with same interface, Unified interface for complexity)\n");

		BigToy bigToy = new BigToy("Elephant");

		bigToy.addSmallToy(new SmallToy("Cat"));
		bigToy.addSmallToy(new SmallToy("Dog"));
		bigToy.build();
	}

	public void testDecorator(){
		System.out.println("Test pattern: Structural / Decorator (Wrapper, Wrapper existing one by extending same interface)\n");

		SimpleControl simpleControl = new SimpleControl("Simple");
		simpleControl.process();

		DecoratorControl decoratorControl = new DecoratorControl("Decorator", simpleControl);
		decoratorControl.process();

		MoreDecoratorControl moreDecoratorControl = new MoreDecoratorControl("MoreDecorator", decoratorControl);
		moreDecoratorControl.process();
	}

	public void testFacade(){
		System.out.println("Test pattern: Structural / Facade (Simple unified interface for group of sub-systems/interfaces)\n");

		FacadeFactory facadeFactory = new FacadeFactory();

		FacadeInterface facadeA = facadeFactory.getFacade("Facade A");
		facadeA.setValue("Cat");

		FacadeInterface facadeB = facadeFactory.getFacade("Facade B");
		facadeB.setValue("Dog");
	}

	public void testFlyweight(){
		System.out.println("Test pattern: Structural / Flyweight (Cache, Reuse)\n");

		ObjectCache objectCache = new ObjectCache();

		objectCache.getSharedObject("Cat");
		objectCache.getSharedObject("Cat");
		objectCache.getSharedObject("Dog");
		objectCache.getSharedObject("Dog");
	}

	public void testProxy(){
		System.out.println("Test pattern: Structural / Proxy (Surrogate, Place-holder for remote service, Same interface binding different implementation)\n");

		SubjectInterface subject = new ProxySubject();

		subject.setName("Tom");
		subject.setAge(6);
		subject.process();
	}

	public static void main(String[] args){
		TestMain theClass = new TestMain();

		System.out.println("************************************************************");
		theClass.testFacotry();
		System.out.println("************************************************************");
		theClass.testAbstractedFacotry();
		System.out.println("************************************************************");
		theClass.testBuilder();
		System.out.println("************************************************************");
		theClass.testPrototype();
		System.out.println("************************************************************");
		theClass.testSingleton();
		System.out.println("************************************************************");

		System.out.println("************************************************************");
		theClass.testChainOfResponsibility();
		System.out.println("************************************************************");
		theClass.testCommand();
		System.out.println("************************************************************");
		theClass.testInterpreter();
		System.out.println("************************************************************");
		theClass.testIterator();
		System.out.println("************************************************************");
		theClass.testMediator();
		System.out.println("************************************************************");
		theClass.testMemento();
		System.out.println("************************************************************");
		theClass.testObserver();
		System.out.println("************************************************************");
		theClass.testState();
		System.out.println("************************************************************");
		theClass.testStrategy();
		System.out.println("************************************************************");
		theClass.testTemplate();
		System.out.println("************************************************************");
		theClass.testVisitor();
		System.out.println("************************************************************");

		System.out.println("************************************************************");
		theClass.testAdapter();
		System.out.println("************************************************************");
		theClass.testBridge();
		System.out.println("************************************************************");
		theClass.testComposite();
		System.out.println("************************************************************");
		theClass.testDecorator();
		System.out.println("************************************************************");
		theClass.testFacade();
		System.out.println("************************************************************");
		theClass.testFlyweight();
		System.out.println("************************************************************");
		theClass.testProxy();
		System.out.println("************************************************************");
	}
}
