package ca.ntro.core.edit_distance;

import java.util.List;

import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.util.ArrayUtils;

public interface EditDistance {
	
	Object[] source();
	Object[] target();

	int editDistance();
	List<Edit> editSequence();
	
	public static EditDistance newEditDistance(Object[] source, Object[] target) {
		return new EditDistanceNtro(source, target);
	}

	public static EditDistance newEditDistance(List<Object> source, List<Object> target) {
		return new EditDistanceNtro(ArrayUtils.fromList(source), ArrayUtils.fromList(target));
	}

	public static EditDistance newEditDistance(String source, String target) {
		return new EditDistanceNtro(ArrayUtils.fromString(source), ArrayUtils.fromString(target));
	}

	public static int editDistance(Object[] source, Object[] target) {
		return newEditDistance(source, target).editDistance();
	}

	public static int editDistance(List<Object> source, List<Object> target) {
		return newEditDistance(source, target).editDistance();
	}

	public static int editDistance(String source, String target) {
		return newEditDistance(source, target).editDistance();
	}

	public static List<Edit> editSequence(Object[] source, Object[] target) {
		return newEditDistance(source, target).editSequence();
	}

	public static List<Edit> editSequence(List<Object> source, List<Object> target) {
		return newEditDistance(source, target).editSequence();
	}

	public static List<Edit> editSequence(String source, String target) {
		return newEditDistance(source, target).editSequence();
	}
	
}
