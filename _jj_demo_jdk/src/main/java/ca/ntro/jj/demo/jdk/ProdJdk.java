package ca.ntro.jj.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.ntro.jj.initialization.InitializerOptions;
import ca.ntro.jj.initialization.InitializerOptionsJdk;
import ca.ntro.jj.static_imports.JjJdk;

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
