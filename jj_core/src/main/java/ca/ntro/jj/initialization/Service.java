package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ServiceId;

public abstract class Service<S extends Service<S>> {

	@SuppressWarnings("unchecked")
	public ServiceId<S> serviceId() {
		return ServiceId.fromServiceClass((Class<S>) this.getClass());
	}
}
