package ca.ntro.core.services;

public class AsserterNull implements Asserter {

	@Override
	public void assertEquals(Object o1, Object o2) {
	}

	@Override
	public void assertTrue(String message, boolean b) {
	}

	@Override
	public void assertArrayEquals(Object[] strings, Object[] segments) {
	}

	@Override
	public void assertFalse(String string, boolean b) {
	}

	@Override
	public void assertFuture(Runnable runnable) {
	}

	@Override
	public void assertFuture(long maxDelay, Runnable runnable) {
	}

	@Override
	public void assertNotNull(String message, Object object) {
	}

}
