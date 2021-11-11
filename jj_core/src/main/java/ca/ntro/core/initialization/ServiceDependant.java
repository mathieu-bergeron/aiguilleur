package ca.ntro.core.initialization;

import ca.ntro.core.values.ServiceMap;

public interface ServiceDependant {

	void requestServices(ServiceRequester serviceRequester);  
	void handleServices(ServiceMap services);

}
