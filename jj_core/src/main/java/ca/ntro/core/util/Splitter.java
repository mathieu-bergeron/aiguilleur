package ca.ntro.core.util;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

	public static List<String> split(String value, String separator) {
		List<String> segments = new ArrayList<>();
		
		StringBuilder input = new StringBuilder(value);
		StringBuilder buffer = new StringBuilder();
		
		while(input.length() > 0) {
			boolean separatorFound = true;
			for(int i = 0; i < separator.length(); i++) {
				if(input.charAt(i) != separator.charAt(i)) {
					separatorFound = false;
					break;
				}
			}
			
			if(separatorFound) {
				segments.add(buffer.toString());
				input.delete(0, separator.length());
				buffer.delete(0, buffer.length());
				
				if(input.length() == 0) {
					segments.add("");
				}

			}else {
				buffer.append(input.charAt(0));
				input.deleteCharAt(0);
			}
		}

		if(buffer.length() > 0) {
			segments.add(buffer.toString());
		}

		return segments;
	}

}
