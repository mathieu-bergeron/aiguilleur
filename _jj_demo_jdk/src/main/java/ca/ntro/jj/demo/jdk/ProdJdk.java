package ca.ntro.jj.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.jj.jdk.globals.JjJdk;
import ca.jj.jdk.initialization.InitializerOptionsJdk;
import ca.ntro.jj.core.static_imports.T;
import ca.ntro.jj.init.InitializerOptions;

public class ProdJdk {
	
	static {
		InitializerOptions options = new InitializerOptionsJdk();
		options.setProd(true);
		
		JjJdk.initializer()
		     .setOptions(options)
		     .executeBlocking();
	}

	public static void main(String[] args) {
		T.trace(ProdJdk.class);
		
		Demo.main();
	}
}
