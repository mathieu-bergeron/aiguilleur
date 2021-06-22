package ca.ntro.jj.app;

import ca.ntro.jj.wrappers.ExceptionHandler;
import ca.ntro.jj.wrappers.Handler;
import ca.ntro.jj.wrappers.future.ResultHandler;

public interface App<A extends App<?>> {

	A addOptions(Options options);
	A initialize();
	A onAppInitialized(Handler voidHandler);
	A onInitializationFailure(ExceptionHandler exceptionHandler);
}
