package ca.ntro.core.identifyers;

import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

import org.junit.BeforeClass;


public class PathTests {
	
	private static ExceptionThrowerMock exceptionThrower = new ExceptionThrowerMock();
	
	@BeforeClass
	public static void initialize() {
		InitializerTest.registerExceptionThrower(exceptionThrower);
	}
	
	@Test
	public void testPathCreation01(){
		
		Path rootPath01 = new Path();
		Path rootPath02 = Path.fromRawPath("");
		Path rootPath03 = Path.fromRawPath("/");
		Path rootPath04 = Path.fromClassname("");
		Path rootPath05 = Path.fromKey("");
		Path rootPath06 = Path.fromFilename("");
		
		Ntro.asserter().assertEquals(rootPath01, rootPath02);
		Ntro.asserter().assertEquals(rootPath01, rootPath03);
		Ntro.asserter().assertEquals(rootPath01, rootPath04);
		Ntro.asserter().assertEquals(rootPath01, rootPath05);
		Ntro.asserter().assertEquals(rootPath01, rootPath06);
		Ntro.asserter().assertEquals(rootPath01.toString(), "/");
	}

	@Test
	public void testPathCreation02(){
		
		Path path01 = new Path();
		path01.addValidName("nom01");
		path01.addValidName("nom02");

		Path path02 = Path.fromRawPath("nom01/nom02");
		Path path03 = Path.fromRawPath("/nom01/nom02");
		Path path04 = Path.fromClassname("nom01.nom02");
		Path path05 = Path.fromKey("nom01¤nom02");
		Path path06 = Path.fromFilename("nom01¤nom02");
		
		Ntro.asserter().assertEquals(path01, path02);
		Ntro.asserter().assertEquals(path01, path03);
		Ntro.asserter().assertEquals(path01, path04);
		Ntro.asserter().assertEquals(path01, path05);
		Ntro.asserter().assertEquals(path01, path06);
		Ntro.asserter().assertEquals(path01.toString(), "/nom01/nom02");
	}

	@Test
	public void testPathCreation03(){
		
		Path path01 = Path.fromSingleName("nom01");
		Path path02 = Path.fromSingleName("nom01");

		Ntro.asserter().assertEquals(path01, path02);
		Ntro.asserter().assertEquals(path01.toString(), "/nom01");
	}

	@Test
	public void testSingleNameViolation(){

		Path.fromSingleName("nom01/nom02");
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));
		
		exceptionThrower.clear();

		Path.fromSingleName("nom01¤nom02");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));
	}

	@Test
	public void testValidCharacters(){
		Path path = Path.fromSingleName("abcdefghijklmnopqrstuvwxyz");
		path = Path.fromSingleName("_-.");
		path = Path.fromSingleName("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}

	@Test
	public void testInvalidCharacters(){
		
		Path path = Path.fromSingleName("é");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("¢");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("É");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("?");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("*");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));
	}

	@Test
	public void testPathAppend01(){
		
		Path result = Path.fromRawPath("/nom01/nom02");
		Path path02 = Path.fromRawPath("/nom03/nom04");

		result.append(path02);

		Path expected = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		
		Ntro.asserter().assertEquals(expected, result);
	}

	@Test
	public void testPathAppend02(){
		
		Path result = Path.fromRawPath("/nom01/nom02/");
		Path path02 = Path.fromRawPath("/nom03/nom04");

		result.append(path02);

		Path expected = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		
		Ntro.asserter().assertEquals(expected, result);
	}

	@Test
	public void testPathStartsWith(){
		
		Path full = Path.fromRawPath("nom01/nom02/nom03/nom04");
		Path prefix = Path.fromRawPath("/nom01/nom02");

		Ntro.asserter().assertTrue(null, full.startsWith(prefix));
	}

	@Test
	public void testIsPrefixOf(){
		
		Path full = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		Path prefix = Path.fromRawPath("nom01/nom02");

		Ntro.asserter().assertTrue(null, prefix.isPrefixOf(full));
	}
}
