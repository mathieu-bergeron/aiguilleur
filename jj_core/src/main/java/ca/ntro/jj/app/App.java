package ca.ntro.jj.app;

import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.wrappers.ExceptionHandler;
import ca.ntro.jj.wrappers.future.Future;
import ca.ntro.jj.wrappers.future.ResultHandler;

public interface App<A extends App<?>> {

	A addOptions(Options options);
	A initialize();
	A onAppInitialized(ResultHandler<ObjectMap> resultHandler);
	A onInitializationFailure(ExceptionHandler exceptionHandler);
}
