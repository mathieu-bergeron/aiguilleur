package ca.ntro.core.services;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionsNtro implements Collections {

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public <I> List<I> sortList(List<I> input) {
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
	
	/**
	 * JSweet: the strange implementation of the method
	 *         below tries to circumvent the fact that
	 *         string comparison in Javascript is not
	 *         the same as in Java (lower/upper cases are
	 *         not considered in the same order).
	 *         
	 *  Java: UpperCase always first (B before a)
	 */

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
		
		String char1 = s1.substring(0,1);
		String char2 = s2.substring(0,1);
		
		if(char1.equals(char2)) {

			return compareStringRecursively(s1.substring(1), s2.substring(1));
		}
		
		if(char1.equals("_")
				&& !char2.equals("_")) {
			
			return -1;
			
		}else if(!char1.equals("_")
				&& char2.equals("_")) {
			
			return +1;
		}
		
		if(isUpperCase(char1)
				&& !isUpperCase(char2)) {

			return -1;

		}else if(!isUpperCase(char1)
				&& isUpperCase(char2)) {
			
			return +1;
		}

		char lower1 = char1.toLowerCase().charAt(0);
		char lower2 = char2.toLowerCase().charAt(0);
		
		if(lower1 < lower2) {

			return -1;

		}else {

			return +1;
		}
	}
	
	private boolean isUpperCase(String character) {
		return character.equals(character.toUpperCase());
	}
	
	
	
}
