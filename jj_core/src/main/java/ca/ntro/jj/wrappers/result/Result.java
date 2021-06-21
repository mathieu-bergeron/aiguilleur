package ca.ntro.jj.wrappers.result;

import ca.ntro.jj.common.ExceptionDelayer;

public interface Result <R extends Object> extends ExceptionDelayer {

	R get();

}
