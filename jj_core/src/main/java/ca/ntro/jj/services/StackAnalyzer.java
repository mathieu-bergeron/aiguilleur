package ca.ntro.jj.services;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ClassIdJj;

public interface StackAnalyzer {
	
	void analyzeCall(Object calledClassOrObject);
	

	static ClassId<StackAnalyzer> classId(){
		return new ClassIdJj<StackAnalyzer>(StackAnalyzer.class);
	}
}
