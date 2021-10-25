package ca.ntro.jj.demo.jdk;


import ca.jj.demo.core.Demo;
import ca.ntro.jj.static_imports.JjJdk;
import ca.ntro.jj.static_imports.T;

public class DevJdk {
	
	static {
		JjJdk.initializer().executeBlocking();
	}

	public static void main(String[] args) {
		T.trace(DevJdk.class);
		
		Demo.main();
	}
}
