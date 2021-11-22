package ca.ntro.core.services;

public class StackAnalyzerNull extends StackAnalyzer {

	@Override
	public void analyzeCall(Object calledClassOrObject) {
	}

	@Override
	public Class<?> callerClass() {
		return null;
	}

}
