package ca.ntro.jj.demo.jdk;

import ca.jj.core.JjJdk;
import ca.jj.core.globals.T;
import ca.jj.demo.core.Demo;

public class DevJdk {
	
	static {
		JjJdk.initializator()
		     .executeBlocking();
	}

	public static void main(String[] args) {
		T.trace(DevJdk.class);
		
		Demo.main();
	}
}
