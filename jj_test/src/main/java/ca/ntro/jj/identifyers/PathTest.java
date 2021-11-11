package ca.ntro.jj.identifyers;

import org.junit.Test;

import ca.ntro.jj.initialization.InitializerTest;
import ca.ntro.jj.initialization.Jj;
import ca.ntro.jj.services.ExceptionThrowerMock;

import org.junit.BeforeClass;


public class PathTest {
	
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
		
		Jj.asserter().assertEquals(rootPath01, rootPath02);
		Jj.asserter().assertEquals(rootPath01, rootPath03);
		Jj.asserter().assertEquals(rootPath01, rootPath04);
		Jj.asserter().assertEquals(rootPath01, rootPath05);
		Jj.asserter().assertEquals(rootPath01, rootPath06);
		Jj.asserter().assertEquals(rootPath01.toString(), "/");
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
		
		Jj.asserter().assertEquals(path01, path02);
		Jj.asserter().assertEquals(path01, path03);
		Jj.asserter().assertEquals(path01, path04);
		Jj.asserter().assertEquals(path01, path05);
		Jj.asserter().assertEquals(path01, path06);
		Jj.asserter().assertEquals(path01.toString(), "/nom01/nom02");
	}

	@Test
	public void testPathCreation03(){
		
		Path path01 = Path.fromSingleName("nom01");
		Path path02 = Path.fromSingleName("nom01");

		Jj.asserter().assertEquals(path01, path02);
		Jj.asserter().assertEquals(path01.toString(), "/nom01");
	}

	@Test
	public void testSingleNameViolation(){

		Path.fromSingleName("nom01/nom02");
		
		Jj.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));
		
		exceptionThrower.clear();

		Path.fromSingleName("nom01¤nom02");

		Jj.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));
	}

	@Test
	public void testInvalidCharacters(){
		
		Path path01 = new Path();

		path01.addName("nom01/nom02");

		Jj.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));

		exceptionThrower.clear();
		
		path01.addName("nom01¤nom02");

		Jj.asserter().assertTrue("Should throw", exceptionThrower.wasThrowned(RuntimeException.class));
	}

	@Test
	public void testPathAppend01(){
		
		Path result = Path.fromRawPath("/nom01/nom02");
		Path path02 = Path.fromRawPath("/nom03/nom04");

		result.append(path02);

		Path expected = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		
		Jj.asserter().assertEquals(expected, result);
	}

	@Test
	public void testPathAppend02(){
		
		Path result = Path.fromRawPath("/nom01/nom02/");
		Path path02 = Path.fromRawPath("/nom03/nom04");

		result.append(path02);

		Path expected = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		
		Jj.asserter().assertEquals(expected, result);
	}

	@Test
	public void testPathStartsWith(){
		
		Path full = Path.fromRawPath("nom01/nom02/nom03/nom04");
		Path prefix = Path.fromRawPath("/nom01/nom02");

		Jj.asserter().assertTrue(null, full.startsWith(prefix));
	}

	@Test
	public void testIsPrefixOf(){
		
		Path full = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		Path prefix = Path.fromRawPath("nom01/nom02");

		Jj.asserter().assertTrue(null, prefix.isPrefixOf(full));
	}
}
