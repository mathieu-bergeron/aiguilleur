package ca.ntro.jj.services.service_factory;

import ca.ntro.jj.services.Service;

public interface ServiceDescriptor<S extends Service> {
	
	Class<S> interfaceClass();
	Class<? extends S> implementationClass();

}
