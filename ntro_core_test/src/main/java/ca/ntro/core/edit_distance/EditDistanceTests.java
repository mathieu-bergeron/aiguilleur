package ca.ntro.core.edit_distance;

import java.util.List;

import org.junit.Test;

import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.tests.NtroTests;

public class EditDistanceTests extends NtroTests {


	@Test
	public void editDistance00() {
		String source = "abc";
		String target = "abc";
		
		int distance = EditDistance.editDistance(source, target);
		
		Ntro.asserter().assertEquals(0, distance);
	}

	@Test
	public void editDistance01() {
		String source = "ac";
		String target = "abc";
		
		int distance = EditDistance.editDistance(source, target);
		
		Ntro.asserter().assertEquals(1, distance);
	}

	@Test
	public void editDistance02() {
		String source = "abc";
		String target = "aBc";
		
		int distance = EditDistance.editDistance(source, target);
		
		Ntro.asserter().assertEquals(1, distance);
	}

	@Test
	public void editSequence00() {
		String source = "abc";
		String target = "abc";
		
		List<Edit> sequence = EditDistance.editSequence(source, target);
		
		Ntro.asserter().assertEquals(0, sequence.size());
	}

	@Test
	public void editSequence01() {
		String source = "";
		String target = "abc";
		
		List<Edit> sequence = EditDistance.editSequence(source, target);
		
		Ntro.asserter().assertEquals(3, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == insert", sequence.get(0).isInsert());
		Ntro.asserter().assertEquals(0, sequence.get(0).asInsert().index());
		Ntro.asserter().assertEquals('a', sequence.get(0).asInsert().value());

		Ntro.asserter().assertTrue("edit[1] == insert", sequence.get(1).isInsert());
		Ntro.asserter().assertEquals(1, sequence.get(1).asInsert().index());
		Ntro.asserter().assertEquals('b', sequence.get(1).asInsert().value());

		Ntro.asserter().assertTrue("edit[2] == insert", sequence.get(2).isInsert());
		Ntro.asserter().assertEquals(2, sequence.get(2).asInsert().index());
		Ntro.asserter().assertEquals('c', sequence.get(2).asInsert().value());
	}

	@Test
	public void editSequence02() {
		String source = "abc";
		String target = "";
		
		List<Edit> sequence = EditDistance.editSequence(source, target);
		
		Ntro.asserter().assertEquals(3, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == delete", sequence.get(0).isDelete());
		Ntro.asserter().assertEquals(0, sequence.get(0).asDelete().index());

		Ntro.asserter().assertTrue("edit[1] == delete", sequence.get(1).isDelete());
		Ntro.asserter().assertEquals(0, sequence.get(1).asDelete().index());

		Ntro.asserter().assertTrue("edit[2] == delete", sequence.get(2).isDelete());
		Ntro.asserter().assertEquals(0, sequence.get(2).asDelete().index());
	}

	@Test
	public void editSequence03() {
		String source = "ac";
		String target = "abc";
		
		List<Edit> sequence = EditDistance.editSequence(source, target);
		
		Ntro.asserter().assertEquals(1, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == insert", sequence.get(0).isInsert());
		Ntro.asserter().assertEquals(1, sequence.get(0).asInsert().index());
		Ntro.asserter().assertEquals('b', sequence.get(0).asInsert().value());
	}

	@Test
	public void editSequence04() {
		String source = "ac";
		String target = "abcd";
		
		List<Edit> sequence = EditDistance.editSequence(source, target);
		
		Ntro.asserter().assertEquals(2, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == insert", sequence.get(0).isInsert());
		Ntro.asserter().assertEquals(1, sequence.get(0).asInsert().index());
		Ntro.asserter().assertEquals('b', sequence.get(0).asInsert().value());

		Ntro.asserter().assertTrue("edit[1] == insert", sequence.get(1).isInsert());
		Ntro.asserter().assertEquals(3, sequence.get(1).asInsert().index());
		Ntro.asserter().assertEquals('d', sequence.get(1).asInsert().value());
	}

	@Test
	public void editSequence05() {
		String source = "1a2b3";
		String target = "ab";
		
		EditDistance editDistance =  EditDistance.newEditDistance(source, target);
		int distance = editDistance.editDistance();
		List<Edit> sequence = editDistance.editSequence();
		
		Ntro.asserter().assertEquals(3, distance);
		Ntro.asserter().assertEquals(3, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == delete", sequence.get(0).isDelete());
		Ntro.asserter().assertEquals(0, sequence.get(0).asDelete().index());

		Ntro.asserter().assertTrue("edit[1] == delete", sequence.get(1).isDelete());
		Ntro.asserter().assertEquals(1, sequence.get(1).asDelete().index());

		Ntro.asserter().assertTrue("edit[2] == delete", sequence.get(2).isDelete());
		Ntro.asserter().assertEquals(2, sequence.get(2).asDelete().index());
	}

	@Test
	public void editSequence06() {
		String source = "1a2b3";
		String target = "aFb";
		
		EditDistance editDistance =  EditDistance.newEditDistance(source, target);
		int distance = editDistance.editDistance();
		List<Edit> sequence = editDistance.editSequence();
		
		Ntro.asserter().assertEquals(3, distance);
		Ntro.asserter().assertEquals(3, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == delete", sequence.get(0).isDelete());
		Ntro.asserter().assertEquals(0, sequence.get(0).asDelete().index());

		Ntro.asserter().assertTrue("edit[1] == update", sequence.get(1).isUpdate());
		Ntro.asserter().assertEquals(1, sequence.get(1).asUpdate().index());
		Ntro.asserter().assertEquals('F', sequence.get(1).asUpdate().value());

		Ntro.asserter().assertTrue("edit[2] == delete", sequence.get(2).isDelete());
		Ntro.asserter().assertEquals(3, sequence.get(2).asDelete().index());
	}

	@Test
	public void editSequence07() {
		String source = "1a2b3";
		String target = "aFGb";
		
		EditDistance editDistance =  EditDistance.newEditDistance(source, target);
		int distance = editDistance.editDistance();
		List<Edit> sequence = editDistance.editSequence();
		
		Ntro.asserter().assertEquals(4, distance);
		Ntro.asserter().assertEquals(4, sequence.size());

		Ntro.asserter().assertTrue("edit[0] == delete", sequence.get(0).isDelete());
		Ntro.asserter().assertEquals(0, sequence.get(0).asDelete().index());

		Ntro.asserter().assertTrue("edit[1] == update", sequence.get(1).isUpdate());
		Ntro.asserter().assertEquals(1, sequence.get(1).asUpdate().index());
		Ntro.asserter().assertEquals('F', sequence.get(1).asUpdate().value());

		Ntro.asserter().assertTrue("edit[2] == update", sequence.get(2).isInsert());
		Ntro.asserter().assertEquals(2, sequence.get(2).asInsert().index());
		Ntro.asserter().assertEquals('G', sequence.get(2).asInsert().value());

		Ntro.asserter().assertTrue("edit[3] == update", sequence.get(3).isInsert());
		Ntro.asserter().assertEquals(3, sequence.get(3).asInsert().index());
		Ntro.asserter().assertEquals('b', sequence.get(3).asInsert().value());
	}


}
