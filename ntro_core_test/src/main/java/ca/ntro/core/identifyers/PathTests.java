package ca.ntro.core.identifyers;

import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;
import ca.ntro.core.services.ExceptionThrowerMock;

import org.junit.BeforeClass;
import org.junit.Ignore;


public class PathTests {
	
	private static ExceptionThrowerMock exceptionThrower = new ExceptionThrowerMock();
	
	@BeforeClass
	public static void initialize() {
		InitializerTest.registerExceptionThrower(exceptionThrower);
	}
	
	@Test
	public void testPathCreation01(){
		
		Path rootPath01 = Path.emptyPath();
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
		
		Path path01 = Path.emptyPath();
		path01.addName("nom01");
		path01.addName("nom02");

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

	@Ignore
	@Test
	public void testSingleNameViolation(){
		exceptionThrower.clear();

		Path.fromSingleName("nom01/nom02");
		
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));
		
		exceptionThrower.clear();

		Path.fromSingleName("nom01¤nom02");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));
	}

	@Test
	public void testValidCharacters(){
		exceptionThrower.clear();
		
		@SuppressWarnings("unused")
		Path path = Path.fromSingleName("abcdefghijklmnopqrstuvwxyz");

		path = Path.fromSingleName("_-.");
		path = Path.fromSingleName("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		Ntro.asserter().assertFalse("Should not throw", exceptionThrower.wasThrown(RuntimeException.class));
	}

	@Test
	public void testInvalidCharacters(){
		exceptionThrower.clear();
		
		@SuppressWarnings("unused")
		Path path = Path.fromSingleName("é");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("¢");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("É");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("?");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromSingleName("*");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));

		exceptionThrower.clear();

		path = Path.fromRawPath("*/**/*");

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(RuntimeException.class));

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

	@Test
	public void testSubPath(){

		Path full = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		Path expectedSub01 = Path.fromRawPath("/nom01/nom02/nom03");
		Path expectedSub02 = Path.fromRawPath("/nom02/nom03/nom04");
		Path expectedSub03 = Path.fromRawPath("nom02/nom03");
		Path expectedSub04 = Path.fromRawPath("nom02");
		Path expectedRoot = Path.fromRawPath("");
		
		Path sub01 = full.subPath(0, full.nameCount()-1);
		Path sub02 = full.subPath(1, full.nameCount());
		Path sub02b = full.subPath(1);
		Path sub03 = full.subPath(1, full.nameCount()-1);
		Path sub04 = expectedSub03.subPath(0, expectedSub03.nameCount()-1);
		Path root = expectedSub04.subPath(0, expectedSub04.nameCount()-1);
		
		Ntro.asserter().assertEquals(expectedSub01, sub01);
		Ntro.asserter().assertEquals(expectedSub02, sub02);
		Ntro.asserter().assertEquals(expectedSub02, sub02b);
		Ntro.asserter().assertEquals(expectedSub03, sub03);
		Ntro.asserter().assertEquals(expectedSub04, sub04);
		Ntro.asserter().assertEquals(expectedRoot, root);
	}
}
