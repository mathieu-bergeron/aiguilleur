package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ServiceId;

public abstract class Service<S extends Service<S>> {

	public ServiceId<S> serviceId() {
		return ServiceId.fromServiceClass(this.getClass());
	}
}
