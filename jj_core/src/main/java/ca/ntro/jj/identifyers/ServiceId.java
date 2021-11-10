package ca.ntro.jj.identifyers;

import ca.ntro.jj.initialization.Service;

public class ServiceId<S extends Service<S>> extends ClassId<S> {

	protected ServiceId(Class<S> _class) {
		super(_class);
	}

	public static <S extends Service<S>> ServiceId<S> fromServiceClass(Class<S> _class){
		ServiceId<S> serviceId = new ServiceId<S>(_class);
		
		serviceId.setEntityPath(Path.fromClassname(_class.getSimpleName()));

		return serviceId;
	}
}
