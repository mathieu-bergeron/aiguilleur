package ca.ntro.core.services;


public class CollectionsJdk extends CollectionsNtro {

	@Override
	protected int compareString(String s1, String s2) {

		Integer i1 = Integer.parseInt(s1);
		Integer i2 = Integer.parseInt(s1);
		
		if(i1 != null
				&& i2 != null) {

			return i1.compareTo(i2);
		}
		
		
		
		// TODO Auto-generated method stub
		return 0;
	}


}
