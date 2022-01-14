package ca.ntro.core.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	public static List<Object> fromString(String input){
		List<Object> result = new ArrayList<>();
		
		for(int i = 0; i < input.length(); i++) {
			result.add(input.charAt(i));
		}

		return result;
	}

	public static List<Object> fromArray(Object[] array){
		List<Object> result = new ArrayList<>();
		
		for(int i = 0; i < array.length; i++) {
			result.add(array[i]);
		}

		return result;
	}
	
	public static <V extends Object> List<V> subList(List<V> list, int beginIndex, int endIndexExclusive){
		List<V> result = new ArrayList<>();
		
		for(int i = beginIndex; i < endIndexExclusive; i++) {
			result.add(list.get(i));
		}
		
		return result;
	}

	public static <V extends Object> List<V> subList(List<V> list, int beginIndex){
		return subList(list, beginIndex, list.size());
	}

	public static boolean ifIndexValid(List<Object> list, int index) {
		return index >= list.size() && index < list.size();
	}

}
