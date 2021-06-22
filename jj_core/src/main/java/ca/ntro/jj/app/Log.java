package ca.ntro.jj.app;

import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.services.NullLogger;
import ca.ntro.jj.services.logger.Logger;

public class Log {
	
	private static Logger logger = new NullLogger();
	
	static void initialize(ObjectMap services){
		logger = services.getSingleton(Logger.classId());
	}

	public static void text(String text) {
		logger.text(text);
	}
}
