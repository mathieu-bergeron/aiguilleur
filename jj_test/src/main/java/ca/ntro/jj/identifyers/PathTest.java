package ca.ntro.jj.identifyers;

import org.junit.Test;

import ca.ntro.jj.exceptions.InvalidCharacterException;

import org.junit.Assert;


public class PathTest {
	
	@Test
	public void testPathCreation01(){
		
		Path rootPath01 = new Path();
		Path rootPath02 = Path.fromRawPath("");
		Path rootPath03 = Path.fromRawPath("/");
		Path rootPath04 = Path.fromClassname("");
		Path rootPath05 = Path.fromKey("");
		Path rootPath06 = Path.fromFilename("");
		
		Assert.assertEquals(rootPath01, rootPath02);
		Assert.assertEquals(rootPath01, rootPath03);
		Assert.assertEquals(rootPath01, rootPath04);
		Assert.assertEquals(rootPath01, rootPath05);
		Assert.assertEquals(rootPath01, rootPath06);
		Assert.assertEquals(rootPath01.toString(), "/");
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
		
		Assert.assertEquals(path01, path02);
		Assert.assertEquals(path01, path03);
		Assert.assertEquals(path01, path04);
		Assert.assertEquals(path01, path05);
		Assert.assertEquals(path01, path06);
		Assert.assertEquals(path01.toString(), "/nom01/nom02");
	}

	@Test
	public void testInvalidCharacters(){
		
		Path path01 = new Path();
		
		Assert.assertThrows(InvalidCharacterException.class, () -> {
			path01.addName("nom01/nom02");
		});

		Assert.assertThrows(InvalidCharacterException.class, () -> {
			path01.addName("nom01¤nom02");
		});
		
	}

	@Test
	public void testPathAppend01(){
		
		Path result = Path.fromRawPath("/nom01/nom02");
		Path path02 = Path.fromRawPath("/nom03/nom04");

		result.append(path02);

		Path expected = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testPathAppend02(){
		
		Path result = Path.fromRawPath("/nom01/nom02/");
		Path path02 = Path.fromRawPath("/nom03/nom04");

		result.append(path02);

		Path expected = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testPathStartsWith(){
		
		Path full = Path.fromRawPath("nom01/nom02/nom03/nom04");
		Path prefix = Path.fromRawPath("/nom01/nom02");

		Assert.assertTrue(full.startsWith(prefix));
	}

	@Test
	public void testIsPrefixOf(){
		
		Path full = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		Path prefix = Path.fromRawPath("nom01/nom02");

		Assert.assertTrue(prefix.isPrefixOf(full));
	}

}
