package ca.ntro.jj.services;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ClassIdJj;

public interface Tracer {

	void trace(Object calledClassOrObject, Object[] arguments);
	
	
	
	static ClassId<Tracer> classId(){
		return new ClassIdJj<Tracer>(Tracer.class);
	}
}
