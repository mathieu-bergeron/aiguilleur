package ca.ntro.jj.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.jj.jdk.globals.JjJdk;
import ca.ntro.jj.core.singletons.T;

public class DevJdk {
	
	static {
		JjJdk.initializator().executeBlocking();
	}

	public static void main(String[] args) {
		T.trace(DevJdk.class);
		
		Demo.main();
	}
}
