package ca.ntro.core.edit_distance;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.util.ArrayUtils;

// https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm

public class EditSequence {
	
	private Object[] source;
	private Object[] target;
	
	private int m;
	private int n;

	private int[][]      distances;
	private EditType[][] types;

	
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
		
		m = source.length + 1;
		n = target.length + 1;
		
		distances = new int[m][n];
		types =     new EditType[m][n];
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				distances[i][j] = 0;
			}
		}
	}


	private void computeResults() {
	    // source prefixes can be transformed into empty string by
	    // dropping all characters
		for(int i = 0; i < m; i++) {
			distances[i][0] = i;
			types[i][0] = EditType.DELETE;
		}
		
	    // target prefixes can be reached from empty source prefix
	    // by inserting every character
		for(int j = 0; j < n; j++) {
			distances[0][j] = j;
			types[0][j] = EditType.INSERT;
		}
		
		for(int j = 0; j < n; j++) {
			for(int i = 0; i < m; i++) {
				int updateCost = 1;
				
				if(source[i].equals(target[j])) {
					updateCost = 0;
				}
				
				int deleteDistance = distances[i-1][j] + 1;
				int insertDistance = distances[i][j-1] + 1;
				int updateDistance = distances[i-1][j-1] + updateCost;
				
				if(deleteDistance < insertDistance
						&& deleteDistance < updateDistance) {
					
					distances[i][j] = deleteDistance;
					types[i][j] = EditType.DELETE;

				}else if(insertDistance < deleteDistance
						&& insertDistance < updateDistance) {

					distances[i][j] = insertDistance;
					types[i][j] = EditType.INSERT;
					
				}else if(updateCost == 1){

					distances[i][j] = updateDistance;
					types[i][j] = EditType.UPDATE;

				}else {

					distances[i][j] = updateDistance;
					types[i][j] = EditType.NONE;
				}
			}
		}
	}

	private List<Edit> compileSequence() {
		List<Edit> editSequence = new ArrayList<Edit>();
		
		int i = m-1;
		int j = n-1;
		
		while(i > 0 && j > 0) {

			Edit edit = currentEdit(i,j);
			
			if(edit != null) {
				editSequence.add(0, edit);
			}
			
			int east = distances[i-1][j];
			int northEast = distances[i-1][j-1];
			int north = distances[i][j-1];
			
			if(east < northEast
					&& east < north) {
				
				i = i - 1;
				
			}else if(northEast < east
					&& northEast < north) {
				
				i = i - 1;
				j = j - 1;
				
			}else {
				
				j = j - 1;
			}
		}
		
		while(i >= 0) {
			Edit edit = currentEdit(i,j);
			
			if(edit != null) {
				editSequence.add(0, edit);
			}
			
			i--;
		}

		while(j >= 0) {
			Edit edit = currentEdit(i,j);
			
			if(edit != null) {
				editSequence.add(0, edit);
			}
			
			j--;
		}

		return editSequence;
	}

	private Edit currentEdit(int i, int j) {
		Edit edit = null;
		
		switch(types[i][j]) {
		
		case INSERT:
			edit = new InsertNtro();
			break;

		case DELETE:
			break;

		case UPDATE:
			break;
		
		
		}
			
			


		return edit;
	}
	



}
