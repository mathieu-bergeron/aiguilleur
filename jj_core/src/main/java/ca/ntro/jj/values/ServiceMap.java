package ca.ntro.jj.values;

import ca.ntro.jj.identifyers.ServiceId;
import ca.ntro.jj.initialization.Service;

public interface ServiceMap {

	<S extends Service<S>> S getService(ServiceId<S> serviceId);

}
