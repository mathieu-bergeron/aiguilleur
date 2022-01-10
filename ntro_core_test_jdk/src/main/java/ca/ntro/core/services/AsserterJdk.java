package ca.ntro.core.services;

import org.junit.Assert;

import ca.ntro.core.initialization.Ntro;

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

	@Override
	public void assertFuture(Runnable runnable) {
		assertFuture(30 * 1000, runnable);
	}

	@Override
	public void assertFuture(long maxDelay, Runnable runnable) {
		long start = Ntro.time().nowMillis();

		runnable.run();

		long delay = 0;

		while(delay < maxDelay) {
			
			Ntro.time().sleep(500);

			delay = Ntro.time().nowMillis() - start;
		}
	}

	@Override
	public void assertNotNull(String message, Object object) {
		Assert.assertNotNull(message, object);
	}

}
