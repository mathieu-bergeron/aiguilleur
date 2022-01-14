package ca.ntro.core.util;

import java.util.List;

public class ArrayUtils {

	public static Object[] fromList(List<?> list) {
		Object[] array = new Object[list.size()];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}

	public static Object[] fromString(String input) {
		Character[] array = new Character[input.length()];

		for(int i = 0; i < array.length; i++) {
			array[i] = input.charAt(i);
		}

		return array;
	}

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
	
	public static <V extends Object> void copyInto(V[] source, V[] destination) {
		int length = source.length;
		if(destination.length < length) {
			length = destination.length;
		}

		for(int i = 0; i < length; i++) {
			destination[i] = source[i];
		}
	}


	public static String[] addString(String[] array, String value) {
		String[] newArray = new String[array.length + 1];
		
		copyInto(array, newArray);
		
		newArray[newArray.length-1] = value;
		
		return newArray;
	}

}
