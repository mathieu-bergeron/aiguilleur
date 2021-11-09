package ca.test;

public class SamePackageButDifferentProjet {
	
	
	static {
		
		ClassInSamePackage testPaquetDifferentsProjets = new ClassInSamePackage();
		
		testPaquetDifferentsProjets.packagePrivateMethod();
		
		testPaquetDifferentsProjets.protectedMethod();

	}

}
