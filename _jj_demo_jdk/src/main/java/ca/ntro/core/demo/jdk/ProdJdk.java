package ca.ntro.core.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.ntro.core.initialization.InitializerOptions;
import ca.ntro.core.initialization.InitializerOptionsJdk;
import ca.ntro.core.static_imports.JjJdk;

public class ProdJdk {
	
	public static void main(String[] args) throws Throwable {

		InitializerOptions options = new InitializerOptionsJdk();
		options.setProd(true);
		
		JjJdk.initializer()
		     .setOptions(options)
		     .executeBlocking();
		
		Demo.main();
	}
}
