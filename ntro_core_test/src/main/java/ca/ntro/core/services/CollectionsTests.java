package ca.ntro.core.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;

public class CollectionsTests {

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
	}

	@Test
	public void sort01() {
		List<String> toSort = new ArrayList<>();
		toSort.add("44");
		toSort.add("12");
		toSort.add("2");
		
		List<String> sorted = Ntro.collections().sortList(toSort);
		
		Ntro.asserter().assertTrue("first is 2", sorted.get(0).equals("2"));
		Ntro.asserter().assertTrue("second is 12", sorted.get(1).equals("12"));
		Ntro.asserter().assertTrue("last is 44", sorted.get(2).equals("44"));
	}

	@Test
	public void sort02() {
		List<String> toSort = new ArrayList<>();
		toSort.add("aa");
		toSort.add("a");
		toSort.add("A");
		toSort.add("Aa");
		toSort.add("Ab");
		toSort.add("bb");
		toSort.add("b");
		toSort.add("B");
		toSort.add("Bb");
		toSort.add("Ba");
		toSort.add("_A");
		toSort.add("_B");
		
		List<String> sorted = Ntro.collections().sortList(toSort);
		
		Ntro.asserter().assertTrue("get(0) == _A", sorted.get(0).equals("_A"));
		Ntro.asserter().assertTrue("get(1) == _B", sorted.get(1).equals("_B"));
		Ntro.asserter().assertTrue("get(2) == A", sorted.get(2).equals("A"));
		Ntro.asserter().assertTrue("get(3) == Aa", sorted.get(3).equals("Aa"));
		Ntro.asserter().assertTrue("get(4) == Ab", sorted.get(4).equals("Ab"));
		Ntro.asserter().assertTrue("get(5) == B", sorted.get(5).equals("B"));
		Ntro.asserter().assertTrue("get(6) == Ba", sorted.get(6).equals("Ba"));
		Ntro.asserter().assertTrue("get(7) == Bb", sorted.get(7).equals("Bb"));
		Ntro.asserter().assertTrue("get(8) == a", sorted.get(8).equals("a"));
		Ntro.asserter().assertTrue("get(9) == aa", sorted.get(9).equals("aa"));
		Ntro.asserter().assertTrue("get(10) == b", sorted.get(10).equals("b"));
		Ntro.asserter().assertTrue("get(11) == bb", sorted.get(11).equals("bb"));
	}

}
