package ca.ntro.jj.core.services;

import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ClassIdJj;

public interface Logger {

	void exception(Throwable e);
	
	
	
	
	

	static ClassId<Logger> classId(){
		return new ClassIdJj<Logger>(Logger.class);
	}
}
