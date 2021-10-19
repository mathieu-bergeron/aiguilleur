package ca.ntro.jj.demo.jdk;

import ca.jj.core.JjJdk;
import ca.jj.core.globals.T;
import ca.jj.demo.core.Demo;

public class ProdJdk {
	
	static {
		InitializationOptions options = new InitializationOptions();
		options.setProd(true);
		
		JjJdk.initializator()
		     .addOptions(options)
		     .executeBlocking();
	}

	public static void main(String[] args) {
		T.trace(ProdJdk.class);
		
		Demo.main();
	}
}
