package ca.ntro.core.edit_distance;

import java.util.List;

import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.util.ListUtils;
import ca.ntro.core.util.StringUtils;

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

	public static String applyEditSequence(String source, List<Edit> editSequence) {
		return StringUtils.fromList(applyEditSequence(ListUtils.fromString(source), editSequence));
	}

	public static Object[] applyEditSequence(Object[] source, List<Edit> editSequence) {
		return ArrayUtils.fromList(applyEditSequence(ListUtils.fromArray(source), editSequence));
	}

	public static List<Object> applyEditSequence(List<Object> source, List<Edit> editSequence) {

		for(Edit edit : editSequence) {

			if(edit.isDelete()) {
				
				source.remove(edit.index());

			}else if(edit.isInsert()) {
				
				source.add(edit.index(), edit.asInsert().value());

			}else if(edit.isUpdate()) {
				
				source.set(edit.index(), edit.asUpdate().value());
			}
		}

		return source;
	}

}
