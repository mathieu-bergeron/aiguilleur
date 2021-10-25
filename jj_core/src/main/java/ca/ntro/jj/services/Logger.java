package ca.ntro.jj.services;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ClassIdJj;

public interface Logger {

	void exception(Throwable e);
	
	
	
	
	

	static ClassId<Logger> classId(){
		return new ClassIdJj<Logger>(Logger.class);
	}
}
