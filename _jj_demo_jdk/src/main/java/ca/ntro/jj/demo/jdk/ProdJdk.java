package ca.ntro.jj.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.ntro.jj.init.InitializerOptions;
import ca.ntro.jj.initialization.InitializerOptionsJdk;
import ca.ntro.jj.static_imports.JjJdk;
import ca.ntro.jj.static_imports.T;

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
