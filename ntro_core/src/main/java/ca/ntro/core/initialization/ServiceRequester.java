package ca.ntro.core.initialization;

import ca.ntro.core.identifyers.ServiceId;

public interface ServiceRequester {

	void requestService(ServiceId serviceId);
}
