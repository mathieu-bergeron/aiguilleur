package ca.ntro.core.services;

public class AsserterJdk implements Asserter {

	@Override
	public void assertEquals(Object o1, Object o2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertTrue(String message, boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertFalse(String string, boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertArrayEquals(Object[] array1, Object[] array2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertFuture(Runnable runnable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertFuture(long maxDelay, Runnable runnable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertNotNull(String message, Object object) {
		if(object == null) {
			System.out.println(message + " should not be null");
			System.exit(1);
		}
	}

}
