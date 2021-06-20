package ca.ntro;


public class TEST_PACKAGES {
	
	static {
		
		//Package _package = NtroMessage.class.getPackage();
		Object o = new Object();
		String packageName = o.getClass().getName();
		System.out.println(packageName);
		
	}

}
