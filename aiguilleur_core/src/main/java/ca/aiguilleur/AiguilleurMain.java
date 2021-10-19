package ca.aiguilleur;

import ca.jj.core.Jj;
import ca.jj.core.JjJdk;

public class AiguilleurMain {

	public static void main() {

		JjJdk.initializator().executeBlocking();

		Jj.trace(AiguilleurMain.class);

		Jj.log("Bonjour!");

	}
}
