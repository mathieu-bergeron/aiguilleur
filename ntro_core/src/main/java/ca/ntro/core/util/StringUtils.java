package ca.ntro.core.util;

import java.util.List;

public class StringUtils {

	public static String fromList(List<Object> list){
		StringBuilder builder = new StringBuilder();
		
		for(Object value : list) {
			builder.append(value);
		}
		
		return builder.toString();
	}

}
