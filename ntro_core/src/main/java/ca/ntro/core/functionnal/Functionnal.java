package ca.ntro.core.functionnal;

public interface Functionnal<V extends Object, R extends Object> {
	
	void visitEach(Reducing<V,R> reducing, GenericVisitor<V> visitor);


	boolean forEach(Reducing<V,R> reducing, GenericTester<V> tester);

	boolean exists(Reducing<V,R> reducing, GenericTester<V> tester);
	

}
