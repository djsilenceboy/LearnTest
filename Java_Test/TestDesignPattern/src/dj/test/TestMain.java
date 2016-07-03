
package dj.test;

import dj.test.behavioral.chainOfResponsibility.HandlerTypeA;
import dj.test.behavioral.chainOfResponsibility.HandlerTypeB;
import dj.test.behavioral.chainOfResponsibility.HandlerTypeBController;
import dj.test.behavioral.command.CommandDocument;
import dj.test.behavioral.interpreter.ContextData;
import dj.test.behavioral.interpreter.InterpreterA;
import dj.test.behavioral.interpreter.InterpreterB;
import dj.test.behavioral.interpreter.InterpreterInterface;
import dj.test.behavioral.iterator.SampleAggregate;
import dj.test.behavioral.iterator.SampleIterator;
import dj.test.behavioral.mediator.Colleague;
import dj.test.behavioral.mediator.ColleagueInterface;
import dj.test.behavioral.mediator.Mediator;
import dj.test.behavioral.mediator.MediatorInterface;
import dj.test.behavioral.memento.Machine;
import dj.test.behavioral.memento.MachineMemento;
import dj.test.behavioral.memento.MementoKeeper;
import dj.test.behavioral.observer.Observer;
import dj.test.behavioral.observer.Subject;
import dj.test.behavioral.state.StateContext;
import dj.test.behavioral.strategy.StrategyA;
import dj.test.behavioral.strategy.StrategyB;
import dj.test.behavioral.strategy.StrategyContext;
import dj.test.behavioral.template.ConcreteA;
import dj.test.behavioral.template.ConcreteB;
import dj.test.behavioral.template.SampleTemplate;
import dj.test.behavioral.visitor.Element;
import dj.test.behavioral.visitor.ElementInterface;
import dj.test.behavioral.visitor.Visitor;
import dj.test.behavioral.visitor.VisitorInterface;
import dj.test.creational.abstractFactory.FactoryInterface;
import dj.test.creational.abstractFactory.ProductTypeAFactory;
import dj.test.creational.abstractFactory.ProductTypeBFactory;
import dj.test.creational.builder.BuilderA;
import dj.test.creational.builder.BuilderB;
import dj.test.creational.builder.BuilderInterface;
import dj.test.creational.builder.Director;
import dj.test.creational.builder.Product;
import dj.test.creational.common.ProductInterface;
import dj.test.creational.common.ProductType;
import dj.test.creational.factory.Factory;
import dj.test.creational.prototype.SampleToy;
import dj.test.creational.singleton.UniqueToy;
import dj.test.creational.singleton.UniqueToyFactory;
import dj.test.structural.adapter.Adaptee;
import dj.test.structural.adapter.Adapter;
import dj.test.structural.adapter.AdapterInterface;
import dj.test.structural.bridge.Bridge;
import dj.test.structural.bridge.BridgeInterface;
import dj.test.structural.bridge.OperaterA;
import dj.test.structural.bridge.OperaterB;
import dj.test.structural.composite.BigToy;
import dj.test.structural.composite.SmallToy;
import dj.test.structural.decorator.DecoratorControl;
import dj.test.structural.decorator.MoreDecoratorControl;
import dj.test.structural.decorator.SimpleControl;
import dj.test.structural.facade.FacadeFactory;
import dj.test.structural.facade.FacadeInterface;
import dj.test.structural.flyweight.ObjectCache;
import dj.test.structural.proxy.ProxySubject;
import dj.test.structural.proxy.SubjectInterface;

public class TestMain
{
	public void testFacotry(){
		System.out.println("Test pattern: Creational / Facotry (Virtual Constructor)\n");

		Factory factory = new Factory();
		ProductType[] productTypes = new ProductType[]{ProductType.ProductTypeA, ProductType.ProductTypeB};

		for (ProductType productType : productTypes) {
			ProductInterface product = factory.create(productType);
			System.out.println("Created product: " + product.getType() + " / " + product.getName());
		}
	}

	public void testAbstractedFacotry(){
		System.out.println("Test pattern: Creational / Abstracted Facotry (Kit)\n");

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

		subject.notify("Rain comming!");
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
