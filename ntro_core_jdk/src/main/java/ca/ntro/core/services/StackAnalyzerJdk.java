package ca.ntro.core.services;

import ca.ntro.core.initialization.Ntro;

public class StackAnalyzerJdk extends StackAnalyzer {

	@Override
	public void analyzeCall(Object calledClassOrObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> callerClass() {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		
		StackTraceElement callerElement = stackTrace[stackTrace.length - 1];
		
		String className = callerElement.getClassName();
		
		Class<?> callerClass = null;

		try {

			callerClass = Class.forName(className);

		} catch (ClassNotFoundException e) {
			
			Ntro.exceptions().throwException(e);
		}
		
		return callerClass;
	}

}
