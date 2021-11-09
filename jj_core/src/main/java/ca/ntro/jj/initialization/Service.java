package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ServiceId;
import ca.ntro.jj.identifyers.ServiceIdJj;

public abstract class Service<S extends Service<S>> {

	public ServiceId<S> serviceId() {
		return new ServiceIdJj<S>(this.getClass());
	}
}
