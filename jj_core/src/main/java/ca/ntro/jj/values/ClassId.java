package ca.ntro.jj.values;

public class ClassId<O extends Object> extends Id {
	
	public static final String CATEGORY = "classes";
	
	public static <O extends Object> ClassId<O> fromClass(Class<O> _class){
		ClassId<O> classId = new ClassId<O>();
		
		classId.setCategoryPath(Path.fromRawPath(CATEGORY));
		classId.setEntityPath(Path.fromClassname(_class.getName()));

		return classId;
	}
}
