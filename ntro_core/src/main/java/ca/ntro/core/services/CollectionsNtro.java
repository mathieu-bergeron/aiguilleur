package ca.ntro.core.services;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionsNtro implements Collections {

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public <I> List<I> sort(List<I> input) {
		List<I> result = new ArrayList<>(input);
		
		result.sort((s1, s2) -> {
			
			if(s1 instanceof String
					&& s2 instanceof String) {
				
				return compareString((String) s1, (String) s2);
				
			} else if(s1 instanceof Comparable
					&& s2 instanceof Comparable) {
				
				return ((Comparable) s1).compareTo((Comparable) s2);
			}
		
			return 0;
		});

		return result;
	}

	protected int compareString(String s1, String s2) {

		try {

			Integer i1 = Integer.parseInt(s1);
			Integer i2 = Integer.parseInt(s2);

			return i1.compareTo(i2);
			
		} catch(NumberFormatException e) {
			
			return compareStringRecursively(s1, s2);
		}
	}

	protected int compareStringRecursively(String s1, String s2) {

		if(s1.isEmpty()
				&& s2.isEmpty()) {
			return 0;

		}else if(s1.isEmpty()
				&& !s2.isEmpty()) {
			return -1;

		}else if(!s1.isEmpty()
				&& s2.isEmpty()) {
			return +1;
		}
		
		char char1 = s1.charAt(0);
		char char2 = s2.charAt(0);

		if(char1 == char2) {

			return compareStringRecursively(s1.substring(1), s2.substring(1));

		}else if(char1 < char2) {

			return -1;

		}else {

			return +1;
		}
	}
}
