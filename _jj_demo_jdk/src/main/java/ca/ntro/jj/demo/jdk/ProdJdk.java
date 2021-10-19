package ca.ntro.jj.demo.jdk;

import ca.jj.core.singletons.T;
import ca.jj.demo.core.Demo;
import ca.jj.jdk.globals.JjJdk;
import ca.jj.jdk.initialization.InitializationOptionsJdk;
import ca.ntro.jj.init.InitializationOptions;

public class ProdJdk {
	
	static {
		InitializationOptions options = new InitializationOptionsJdk();
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
