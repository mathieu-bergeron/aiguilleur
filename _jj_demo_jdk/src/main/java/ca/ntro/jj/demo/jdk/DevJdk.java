package ca.ntro.jj.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.ntro.jj.static_imports.JjJdk;

public class DevJdk {

	public static void main(String[] args) throws Throwable {

		JjJdk.initializer().executeBlocking();

		Demo.main();
	}
}
