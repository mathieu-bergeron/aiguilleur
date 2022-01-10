package ca.ntro.core.services;

public interface Asserter {
	
	void assertEquals(Object o1, Object o2);
	void assertTrue(String message, boolean b);
	void assertFalse(String string, boolean b);
	void assertArrayEquals(Object[] array1, Object[] array2);
	
	void assertFuture(Runnable runnable);
	void assertFuture(long maxDelay, Runnable runnable);
	void assertNotNull(String message, Object object);

}
