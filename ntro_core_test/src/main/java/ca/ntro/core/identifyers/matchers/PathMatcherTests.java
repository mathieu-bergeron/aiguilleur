package ca.ntro.core.identifyers.matchers;

import org.junit.Test;

import ca.ntro.core.identifyers.Path;
import ca.ntro.core.initialization.Ntro;

public class PathMatcherTests {

	@Test
	public void testPathMatcherMatches(){
		
		Path path01 = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		PathMatcher matcher01 = new PathMatcherNtro("nom04");
		PathMatcher matcher02 = new PathMatcherNtro("nom03/nom04");
		PathMatcher matcher03 = new PathMatcherNtro("*/nom04");
		PathMatcher matcher04 = new PathMatcherNtro("nom03/*");
		PathMatcher matcher05 = new PathMatcherNtro("**/nom04");
		PathMatcher matcher06 = new PathMatcherNtro("nom01/**/nom04");
		
		Ntro.asserter().assertTrue("Should match", matcher01.matches(path01));
		Ntro.asserter().assertTrue("Should match", matcher02.matches(path01));
		Ntro.asserter().assertTrue("Should match", matcher03.matches(path01));
		Ntro.asserter().assertTrue("Should match", matcher04.matches(path01));
		Ntro.asserter().assertTrue("Should match", matcher05.matches(path01));
		Ntro.asserter().assertTrue("Should match", matcher06.matches(path01));
	}

	@Test
	public void testPathMatcherDoesNotMatch(){
		
		Path path01 = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		PathMatcher matcher01 = new PathMatcherNtro("asdf");
		PathMatcher matcher02 = new PathMatcherNtro("asdf/nom04");
		PathMatcher matcher03 = new PathMatcherNtro("nom03/asdf");
		PathMatcher matcher04 = new PathMatcherNtro("*/asdf");
		PathMatcher matcher05 = new PathMatcherNtro("**/adsf");
		PathMatcher matcher06 = new PathMatcherNtro("nom01/**/asdf");
		
		Ntro.asserter().assertFalse("Should not match", matcher01.matches(path01));
		Ntro.asserter().assertFalse("Should not match", matcher02.matches(path01));
		Ntro.asserter().assertFalse("Should not match", matcher03.matches(path01));
		Ntro.asserter().assertFalse("Should not match", matcher04.matches(path01));
		Ntro.asserter().assertFalse("Should not match", matcher05.matches(path01));
		Ntro.asserter().assertFalse("Should not match", matcher06.matches(path01));
	}

}
