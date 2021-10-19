package ca.aiguilleur;

import ca.jj.core.Jj;
import ca.jj.core.JjJdk;
import ca.jj.demo.DemoMain;

public class DemoJdk {
	
	static {
		JjJdk.initializeBlocking();
	}

	public static void main(String[] args) {
		Jj.trace(DemoJdk.class);
		
		DemoMain.main();
	}
}
