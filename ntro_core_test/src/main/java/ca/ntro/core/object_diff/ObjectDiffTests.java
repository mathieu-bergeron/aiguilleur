package ca.ntro.core.object_diff;


import java.util.List;

import org.junit.Test;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.object_diff.updates.ObjectUpdate;
import ca.ntro.core.tests.NtroTests;
import ca.ntro.core.util.ListUtils;

public class ObjectDiffTests extends NtroTests {
	
	@Test
	public void objectDiff00() {
		Obj01 source = new Obj01();
		Obj01 target = new Obj01();
		
		source.setAttr01("a");
		target.setAttr01("a");
		
		List<ObjectUpdate> updates = ObjectDiff.diff(source, target);
		
		Ntro.asserter().assertEquals(0, updates.size());
	}

	@Test
	public void objectDiff01() {
		Obj01 source = new Obj01();
		Obj01 target = new Obj01();
		
		source.setAttr01("a");
		target.setAttr01("b");
		
		List<ObjectUpdate> updates = ObjectDiff.diff(source, target);
		
		Ntro.asserter().assertEquals(1, updates.size());

		Ntro.asserter().assertTrue("update[0] == modify", updates.get(0).isModify());
		Ntro.asserter().assertEquals("b", updates.get(0).asModify().value());
	}

	@Test
	public void objectDiffList() {
		Obj01 source = new Obj01();
		Obj01 target = new Obj01();
		
		source.setAttr02(ListUtils.fromString("abc"));
		target.setAttr02(ListUtils.fromString("ab2c"));
		
		List<ObjectUpdate> updates = ObjectDiff.diff(source, target);
		
		Ntro.asserter().assertEquals(1, updates.size());

		Ntro.asserter().assertTrue("update[0] == insert", updates.get(0).isInsert());
		Ntro.asserter().assertEquals("/attr02/2", updates.get(0).valuePath().toRawPath());
		Ntro.asserter().assertEquals('2', updates.get(0).asInsert().value());
	}
}
