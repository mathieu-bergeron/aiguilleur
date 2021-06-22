package ca.ntro.jj.app;

import ca.ntro.jj.common.identifyiers.ClassId;
import ca.ntro.jj.common.identifyiers.ClassIdJj;

public interface Options {
	
	void setIsProd(boolean isProd);

	boolean isProd();

	static ClassId<Options> classId() {
		return new ClassIdJj<>(Options.class);
	}

}
