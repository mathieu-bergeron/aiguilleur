package ca.ntro.jj.services.service_factory;

import ca.ntro.jj.services.Service;

public class ServiceDescriptorJj<S extends Service> implements ServiceDescriptor<S> {
	
	private Class<S> interfaceClass;
	private Class<? extends S> implementationClass;

	public ServiceDescriptorJj(Class<S> interfaceClass, Class<? extends S> implementationClass) {
		this.interfaceClass = interfaceClass;
		this.implementationClass = implementationClass;
	}

	@Override
	public Class<S> interfaceClass() {
		return interfaceClass;
	}

	@Override
	public Class<? extends S> implementationClass() {
		return implementationClass;
	}
}
