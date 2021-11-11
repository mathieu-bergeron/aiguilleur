package ca.ntro.core.values;

import ca.ntro.core.identifyers.ServiceId;
import ca.ntro.core.initialization.Service;

public interface ServiceMap {

	<S extends Service<S>> S getService(ServiceId<S> serviceId);

}
