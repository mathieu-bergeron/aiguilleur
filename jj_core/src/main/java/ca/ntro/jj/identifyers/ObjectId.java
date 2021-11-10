package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.ClassId;

public interface ObjectId<O extends Object> extends ClassId<O>, SimpleId {
	
	ClassId<O> classId();

}
