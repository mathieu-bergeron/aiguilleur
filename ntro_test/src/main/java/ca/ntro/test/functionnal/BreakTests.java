package ca.ntro.test.functionnal;


import static ca.ntro.jj.assertions.Factory.that;

import org.junit.Test;

import ca.ntro.core.models.StoredList;
import ca.ntro.core.models.lambdas.Break;
import ca.ntro.jj.services.Ntro;


public class BreakTests {
	
	private class IntList extends StoredList<Integer> {
	}

	@Test
	public void testBreakWorks() {
		
		IntList intList = new IntList();

		intList.addItem(5);
		intList.addItem(5);
		intList.addItem(5);

		int sum = intList.reduceTo(Integer.class, 0, (index, val, accumulator) -> {
			return accumulator + val;
		});
		
		int interuptedSum = intList.reduceTo(Integer.class, 0, (index, val, accumulator) -> {
			if(accumulator > 5) {
				throw new Break();
			}
			return accumulator + val;
		});

		Ntro.verify(that(sum).isNotEqualTo(interuptedSum));
	}

}
