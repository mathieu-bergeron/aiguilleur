package ca.ntro.core.identifyers.matchers;

import org.junit.Test;

import ca.ntro.core.identifyers.Path;
import ca.ntro.core.initialization.Ntro;

public class PathMatcherTests {

	@Test
	public void testPathMatcher01(){
		
		Path path01 = Path.fromRawPath("/nom01/nom02/nom03/nom04");
		PathMatcher matcher01 = new PathMatcherNtro("nom04");
		PathMatcher matcher02 = new PathMatcherNtro("asdf");
		
		Ntro.asserter().assertTrue("Should match", matcher01.matches(path01));
		Ntro.asserter().assertFalse("Should not match", matcher02.matches(path01));
	}

}
