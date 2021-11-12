package ca.ntro.core.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	
	public static <V extends Object> List<V> subList(List<V> list, int beginIndex, int endIndexExclusive){
		List<V> result = new ArrayList<>();
		
		for(int i = beginIndex; i < endIndexExclusive; i++) {
			result.add(list.get(i));
		}
		
		return result;
	}

}
