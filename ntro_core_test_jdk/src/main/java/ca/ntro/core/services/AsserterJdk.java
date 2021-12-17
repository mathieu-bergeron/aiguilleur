package ca.ntro.core.services;

import org.junit.Assert;

public class AsserterJdk implements Asserter {

	@Override
	public void assertEquals(Object o1, Object o2) {
		Assert.assertEquals(o1,o2);
	}

	@Override
	public void assertTrue(String message, boolean b) {
		Assert.assertTrue(message, b);
	}

	@Override
	public void assertArrayEquals(Object[] expecteds, Object[] actuals) {
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Override
	public void assertFalse(String string, boolean b) {
		Assert.assertFalse(string, b);
		
	}

}
