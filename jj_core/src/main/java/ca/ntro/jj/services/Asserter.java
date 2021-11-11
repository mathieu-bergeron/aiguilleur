package ca.ntro.jj.services;

public interface Asserter {
	
	void assertEquals(Object o1, Object o2);
	void assertTrue(String message, boolean b);
	void assertArrayEquals(Object[] strings, Object[] segments);

}
