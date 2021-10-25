package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.future.Future;

public interface Initializor {
	
	<O extends Object> void registerSingleton(ClassId<O> classId, O object);

	<O extends Object> void registerObject(ObjectId<O> objectId, O object);

	<O extends Object> void registerInitializationTask(ClassId<O> classId, InitializationTask initializationTask);

	<O extends Object> InitializationTask initializationTask(ClassId<O> classId);

	<O extends Object> Future<O> initializedSingleton(ClassId<O> classId);

	<O extends Object> Future<O> initializedObject(ObjectId<O> objectId);

	Future<ObjectMap> initializedObjects();

}
