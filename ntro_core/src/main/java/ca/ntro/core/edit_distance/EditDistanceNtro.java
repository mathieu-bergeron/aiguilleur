package ca.ntro.core.edit_distance;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.edit_distance.edits.DeleteNtro;
import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.edit_distance.edits.InsertNtro;
import ca.ntro.core.edit_distance.edits.ModifyNtro;
import ca.ntro.core.stream.Stream;

// https://en.wikipedia.org/wiki/Wagner%E2%80%93Fischer_algorithm

public class EditDistanceNtro implements EditDistance {
	
	private Object[] source;
	private Object[] target;
	
	private int m;
	private int n;

	private int[][] distances = null;
	
	private List<Edit> editSequence = null;

	public Object[] getSource() {
		return source;
	}

	public void setSource(Object[] source) {
		this.source = source;
	}

	public Object[] getTarget() {
		return target;
	}


	public void setTarget(Object[] target) {
		this.target = target;
	}


	EditDistanceNtro(Object[] source, Object[] target) {
		setSource(source);
		setTarget(target);
		initialize();
	}

	public void initialize() {
		m = getSource().length + 1;
		n = getTarget().length + 1;
	}

	private void computeDistances() {
		distances = new int[m][n];

		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				distances[i][j] = 0;
			}
		}
		
	    // source prefixes can be transformed into empty string by
	    // dropping all characters
		for(int i = 1; i < m; i++) {
			distances[i][0] = i;
		}
		
	    // target prefixes can be reached from empty source prefix
	    // by inserting every character
		for(int j = 1; j < n; j++) {
			distances[0][j] = j;
		}
		
		for(int j = 1; j < n; j++) {
			for(int i = 1; i < m; i++) {

				int modifyCost;
				
				if(!source[i-1].equals(target[j-1])) {

					modifyCost = 1;

				}else {

					modifyCost = 0;
				}
				
				int deleteDistance = distances[i-1][j] + 1;
				int insertDistance = distances[i][j-1] + 1;
				int modifyDistance = distances[i-1][j-1] + modifyCost;
				
				if(deleteDistance < insertDistance
						&& deleteDistance < modifyDistance) {
					
					distances[i][j] = deleteDistance;

				}else if(insertDistance < deleteDistance
						&& insertDistance < modifyDistance) {

					distances[i][j] = insertDistance;

				}else {

					distances[i][j] = modifyDistance;

				}
			}
		}
	}

	private void compileSequence() {
		editSequence = new ArrayList<>();
		
		if(m == 1) {

			compileInsertSequence();

		}else if(n == 1) {

			compileDeleteSequence();
			
		}else {
			compileMixedSequence();
		}
	}

	private void compileInsertSequence() {
		for(int i = 0; i < target.length; i++) {
			editSequence.add(new InsertNtro(i, target[i]));
		}
	}

	private void compileDeleteSequence() {
		for(int i = 0; i < source.length; i++) {
			editSequence.add(new DeleteNtro(0));
		}
	}

	private void compileMixedSequence() {
		int i = m-1;
		int j = n-1;
		
		while(i > 0 && j > 0) {
			
			Edit currentEdit = null;
			
			int deleteDistance = distances[i-1][j];
			int insertDistance = distances[i][j-1];
			int modifyDistance = distances[i-1][j-1];
			
			if(deleteDistance < modifyDistance
					&& deleteDistance < insertDistance) {

				i = i - 1;
				currentEdit = new DeleteNtro(j);
				
			}else if(insertDistance < deleteDistance
					&& insertDistance < modifyDistance) {
				
				j = j - 1;
				currentEdit = new InsertNtro(j, target[j]);

			}else {
				
				i = i - 1;
				j = j - 1;
				
				if(!source[i].equals(target[j])) {

					currentEdit = new ModifyNtro(j, target[j]);
				}
			}

			if(currentEdit != null) {
				editSequence.add(0, currentEdit);
			}
		}
		
		while(i > 0) {
			i--;
			editSequence.add(0, new DeleteNtro(0));
		}

		while(j > 0) {
			j--;
			editSequence.add(0, new InsertNtro(j, target[j]));
		}
	}

	@Override
	public Object[] source() {
		return getSource();
	}

	@Override
	public Object[] target() {
		return getTarget();
	}

	@Override
	public int editDistance() {
		if(distances == null) {
			computeDistances();
		}

		return distances[m-1][n-1];
	}

	@Override
	public Stream<Edit> editSequence() {
		if(distances == null) {
			computeDistances();
		}

		if(editSequence == null) {
			compileSequence();
		}

		return Stream.fromList(editSequence);
	}
}
