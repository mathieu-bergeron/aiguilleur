package ca.ntro.jj.core.identifyers;

public interface ObjectId<O extends Object> extends ClassId<O>, SimpleId {
	
	ClassId<O> classId();

}
