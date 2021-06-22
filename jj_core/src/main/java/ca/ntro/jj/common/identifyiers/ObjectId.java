package ca.ntro.jj.common.identifyiers;

public interface ObjectId<O extends Object> extends ClassId<O>, SimpleId {
	
	ClassId<O> classId();

}
