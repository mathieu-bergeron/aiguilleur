package ca.ntro.core.services;

import ca.ntro.core.initialization.Service;

public abstract class StackAnalyzer extends Service<StackAnalyzer> {
	
	public abstract void analyzeCall(Object calledClassOrObject);

	public abstract Class<?> callerClass();
}
