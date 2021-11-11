package ca.ntro.core.initialization;

import ca.ntro.core.identifyers.ServiceId;

public abstract class Service<S extends Service<S>> {

	@SuppressWarnings("unchecked")
	public ServiceId<S> serviceId() {
		return ServiceId.fromServiceClass((Class<S>) this.getClass());
	}
}
