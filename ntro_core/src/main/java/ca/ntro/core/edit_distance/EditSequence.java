package ca.ntro.core.edit_distance;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.edit_distance.edits.DeleteNtro;
import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.edit_distance.edits.InsertNtro;
import ca.ntro.core.edit_distance.edits.UpdateNtro;
import ca.ntro.core.util.ArrayUtils;

// https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm

public class EditSequence {
	
	private Object[] source;
	private Object[] target;
	
	private int m;
	private int n;

	private EditDistance[][] distances;

	
	public static List<Edit> editSequence(List<Object> source, List<Object> target)  {
		return new EditSequence().computeEditSequence(source, target);
	}

	private List<Edit> computeEditSequence(List<Object> sourceList, List<Object> targetList)  {
		
		initialize(sourceList, targetList);

		computeResults();
		
		return compileSequence();
	}

	private void initialize(List<Object> sourceList, List<Object> targetList) {
		source = ArrayUtils.fromList(sourceList);
		target = ArrayUtils.fromList(targetList);
		
		m = source.length;
		n = target.length;
		
		// FIXME: the algorithm is expressed with 1..n for input arrays
		distances = new EditDistance[m+1][n+1];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				distances[i][j] = new EditDistance();
			}
		}
	}


	private void computeResults() {
	    // source prefixes can be transformed into empty string by
	    // dropping all characters
		for(int i = 0; i < m; i++) {
			distances[i][0].setDistance(i);
			distances[i][0].setEdit(new DeleteNtro(i));
		}
		
	    // target prefixes can be reached from empty source prefix
	    // by inserting every character
		for(int j = 0; j < n; j++) {
			distances[0][j].setDistance(j);
			distances[0][j].setEdit(new InsertNtro(j, target[j]));
		}
		
		for(int j = 0; j < n; j++) {
			for(int i = 0; i < m; i++) {
				int updateCost = 1;
				
				if(source[i].equals(target[j])) {
					updateCost = 0;
				}
				
				int deleteDistance = distances[i-1][j].getDistance() + 1;
				int insertDistance = distances[i][j-1].getDistance() + 1;
				int updateDistance = distances[i-1][j-1].getDistance() + updateCost;
				
				if(deleteDistance < insertDistance
						&& deleteDistance < updateDistance) {
					
					distances[i][j].setDistance(deleteDistance);
					distances[i][j].setEdit(new DeleteNtro(i));

				}else if(insertDistance < deleteDistance
						&& insertDistance < updateDistance) {

					distances[i][j].setDistance(insertDistance);
					distances[i][j].setEdit(new InsertNtro(i, target[j]));
					
				}else {

					distances[i][j].setDistance(updateDistance);
					distances[i][j].setEdit(new UpdateNtro(i, target[j]));
				}
			}
		}
	}

	private List<Edit> compileSequence() {
		List<Edit> editSequence = new ArrayList<Edit>();

		return editSequence;
	}



}
