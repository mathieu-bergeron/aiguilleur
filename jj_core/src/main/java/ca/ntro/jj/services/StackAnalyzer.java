package ca.ntro.jj.services;

import ca.ntro.jj.initialization.Service;

public abstract class StackAnalyzer extends Service<StackAnalyzer> {
	
	public abstract void analyzeCall(Object calledClassOrObject);
}
