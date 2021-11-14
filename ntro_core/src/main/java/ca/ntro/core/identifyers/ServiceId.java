package ca.ntro.core.identifyers;

import ca.ntro.core.initialization.Service;
import ca.ntro.core.path.Path;

public class ServiceId<S extends Service<S>> extends ClassId<S> {

	protected ServiceId(Class<S> _class) {
		super(_class);
	}

	public static <S extends Service<S>> ServiceId<S> fromServiceClass(Class<S> _class){
		ServiceId<S> serviceId = new ServiceId<S>(_class);
		
		serviceId.setEntityPath(Path.fromSingleName(_class.getSimpleName()));

		return serviceId;
	}
}
