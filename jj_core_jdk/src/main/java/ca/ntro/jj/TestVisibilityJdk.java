package ca.ntro.jj;

public class TestVisibilityJdk {
	
	static {
		
		TestVisibility.testPackagePrivate();
		TestVisibility.testProtected();
		TestVisibility.testPublic();

	}

}
