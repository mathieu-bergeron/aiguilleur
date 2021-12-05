package ca.ntro.core.stream;

public interface GenericMapper<I extends Object> {
	
	I map(I item) throws Throwable;

}
