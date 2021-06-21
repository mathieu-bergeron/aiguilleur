package ca.ntro.jj.managed_objects;

import ca.ntro.jj.tasks.base.Task;


/* TODO: merge with ServiceFactory, which should not be reserved for services
 * 
 * 
 */
public interface Factory {
	
	public static final String SINGLETON_ID = "_S";

	<MO extends ManagedObject> void registerObject(ObjectDescriptor<MO> descriptor, MO object);

	/* TODO: a SubFactory creates an instantiationTask
	 *       for a some type of ManagedObject
	 */
	<MO extends ManagedObject> void registerSubFactory(ClassDescriptor<MO> descriptor, SubFactory<MO> factory);
	
	Task instantiationTask(Class<? extends ManagedObject> _class);
	
	

}
