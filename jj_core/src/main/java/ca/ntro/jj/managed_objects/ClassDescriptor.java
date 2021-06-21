package ca.ntro.jj.managed_objects;

public interface ClassDescriptor<MO extends ManagedObject> {

	Class<MO> _class();

}
