package ca.ntro.core.util;

public class ArrayUtils {

	public static boolean containsString(String[] array, String value) {
		return contains(array,value);
	}
	
	public static boolean contains(Object[] array, Object value) {
		boolean contains = false;
		
		for(int i = 0; i < array.length; i++) {
			if(array[i].equals(value)) {
				contains = true;
				break;
			}
		}
		
		return contains;
	}
}
