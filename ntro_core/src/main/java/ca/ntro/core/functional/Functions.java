package ca.ntro.core.functional;

import ca.ntro.core.wrappers.result.Result;

public interface Functions<V extends Object,
						   R extends Object,
						   VISITOR extends GenericVisitor<V>,
                           TESTER extends GenericTester<V>,
                           REDUCER extends GenericReducer<V,R>> {
	
	V findFirst(TESTER tester);

	boolean ifAll(TESTER tester);
	boolean ifExists(TESTER tester);
	
	void forEach(VISITOR visitor);
	Result<R> reduceAll(REDUCER reducer);

}
