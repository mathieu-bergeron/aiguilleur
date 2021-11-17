package ca.ntro.core.reflection;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class ObjectGraphTests {

	private static ExceptionThrowerMock exceptionThrower = new ExceptionThrowerMock();
	
	protected ExceptionThrowerMock exceptionThrower() {
		return exceptionThrower;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
		InitializerTest.registerExceptionThrower(exceptionThrower);
	}
	
	@Test
	public void testObjectGraph01() {
		
		TestObject01 o = new TestObject01();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		
		List<Object> subValues = new ArrayList<>();

		graph.forEachNode(n -> {
			
			subValues.add(n.value().object());
		});
		
		Ntro.asserter().assertEquals(2, subValues.size());
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o));
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o.getAttribute01()));
	}

	@Test
	public void testObjectGraph02() {
		
		TestObject02 o = new TestObject02();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		
		List<Object> subValues = new ArrayList<>();

		graph.forEachNode(n -> {
			
			subValues.add(n.value().object());
		});
		
		Ntro.asserter().assertEquals(3, subValues.size());
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o));
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o.getTestObject01()));
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o.getTestObject01().getAttribute01()));
	}

	@Test
	public void testObjectCycle() {
		
		TestObjectCycle o = new TestObjectCycle();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		
		List<Object> subValues = new ArrayList<>();

		graph.forEachNode(n -> {
			
			subValues.add(n.value().object());
		});
		
		Ntro.asserter().assertEquals(1, subValues.size());
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o));
	}

}
