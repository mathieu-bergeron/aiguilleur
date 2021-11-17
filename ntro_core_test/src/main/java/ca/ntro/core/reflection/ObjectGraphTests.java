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
		
		TestObject o = new TestObject();
		
		ObjectGraph graph = Ntro.reflectionService().objectGraph(o);
		
		List<Object> subValues = new ArrayList<>();

		graph.forEachNode(n -> {
			
			subValues.add(n.value().object());
		});
		
		Ntro.asserter().assertTrue("Should contain", subValues.contains(o.getAttribute01()));
		
		
		
	}

}
