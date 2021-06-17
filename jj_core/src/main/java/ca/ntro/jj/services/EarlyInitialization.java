package ca.ntro.jj.services;

import ca.ntro.jj.DocumentPath;
import ca.ntro.jj.Path;
import ca.ntro.jj.ValuePath;
import ca.ntro.jj.date.NtroDate;
import ca.ntro.jj.date.NtroDayOfWeek;
import ca.ntro.jj.date.NtroTimeOfDay;
import ca.ntro.jj.introspection.Introspector;
import ca.ntro.jj.regex.RegEx;
import ca.ntro.jj.trace.T;

public abstract class EarlyInitialization {

	public void performInitialization() {
		registerSerializableClasses();


		Introspector introspector = provideIntrospector();
		T.__registerIntrospector(introspector);
		Ntro.registerIntrospector(introspector);

		Logger logger = provideLogger();
		T.__registerLogger(logger);
		Ntro.registerLogger(logger);
		
		Ntro.registerAppCloser(provideAppCloser());
		Ntro.registerRegEx(provideRegEx());

		Ntro.registerCollectionsService(provideCollectionsService());

		Ntro.registerConfigService(provideConfigService());
	}

	protected abstract Introspector provideIntrospector();
	protected abstract Logger provideLogger();
	protected abstract AppCloser provideAppCloser();
	protected abstract RegEx provideRegEx();
	protected abstract CollectionsService provideCollectionsService();
	protected abstract ConfigService provideConfigService();

	protected void registerSerializableClasses() {
		/*
		Ntro.registerSerializableClass(NtroUser.class);
		Ntro.registerSerializableClass(NtroSession.class);
		Ntro.registerSerializableClass(NtroSessionData.class);
		Ntro.registerSerializableClass(NtroDate.class);
		Ntro.registerSerializableClass(NtroTimeOfDay.class);
		Ntro.registerSerializableClass(NtroDayOfWeek.class);

		Ntro.registerSerializableClass(NtroRegisterSocketMessage.class);
		Ntro.registerSerializableClass(NtroGetModelMessage.class);
		Ntro.registerSerializableClass(NtroSetModelMessage.class);
		Ntro.registerSerializableClass(NtroUpdateSessionMessage.class);
		Ntro.registerSerializableClass(NtroInvokeValueMethodMessage.class);
		Ntro.registerSerializableClass(NtroErrorMessage.class);

		Ntro.registerSerializableClass(Path.class);
		Ntro.registerSerializableClass(DocumentPath.class);
		Ntro.registerSerializableClass(ValuePath.class);

		Ntro.registerSerializableClass(StoredBoolean.class);
		Ntro.registerSerializableClass(StoredString.class);
		Ntro.registerSerializableClass(StoredDouble.class);
		Ntro.registerSerializableClass(StoredInteger.class);
		Ntro.registerSerializableClass(StoredLong.class);
		Ntro.registerSerializableClass(StoredProperty.class);
		Ntro.registerSerializableClass(StoredList.class);
		Ntro.registerSerializableClass(StoredMap.class);
		*/
	}
}
