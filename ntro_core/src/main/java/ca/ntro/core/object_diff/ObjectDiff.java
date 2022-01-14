package ca.ntro.core.object_diff;

import java.util.List;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.object_diff.updates.ObjectUpdate;
import ca.ntro.core.reflection.object_graph.ObjectGraph;

public interface ObjectDiff {
	
	List<ObjectUpdate> updates();
	
	public static ObjectDiff newObjectDiff(Object source, Object target){
		return newObjectDiff(Ntro.reflectionService().objectGraph(source), Ntro.reflectionService().objectGraph(target));
	}

	private static ObjectDiff newObjectDiff(ObjectGraph source, ObjectGraph target){
		return new ObjectDiffNtro(source, target);
	}
	
	public static List<ObjectUpdate> diff(Object source, Object target){
		return newObjectDiff(source, target).updates();
	}
	
	

}
