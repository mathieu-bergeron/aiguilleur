package ca.ntro.core.reflection;

public interface MethodNameReducer<R extends Object> {
	
	R reduceMethodName(R accumulator, String methodName) throws Throwable;

}
