package ca.aiguilleur;

import ca.ntro.jj.JjJSweet;

public class JavaMainJSweet {
	
	public static void main(String[] args) {
		
		JjJSweet.initialize()

		        .handleResult(services -> {

		        	AiguilleurMain.main(services);

		        })

		        .handleException(e -> {

		        	e.printStackTrace();

				});
	}
}
