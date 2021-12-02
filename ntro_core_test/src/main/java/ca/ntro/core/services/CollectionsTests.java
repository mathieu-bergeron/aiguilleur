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
		
		List<String> sorted = Ntro.collections().sortList(toSort);
		
		Ntro.asserter().assertTrue("get(0) == A", sorted.get(0).equals("A"));
		Ntro.asserter().assertTrue("get(1) == Aa", sorted.get(1).equals("Aa"));
		Ntro.asserter().assertTrue("get(2) == Ab", sorted.get(2).equals("Ab"));
		Ntro.asserter().assertTrue("get(3) == B", sorted.get(3).equals("B"));
		Ntro.asserter().assertTrue("get(4) == Ba", sorted.get(4).equals("Ba"));
		Ntro.asserter().assertTrue("get(5) == Bb", sorted.get(5).equals("Bb"));
		Ntro.asserter().assertTrue("get(6) == a", sorted.get(6).equals("a"));
		Ntro.asserter().assertTrue("get(6) == aa", sorted.get(7).equals("aa"));
		Ntro.asserter().assertTrue("get(8) == b", sorted.get(8).equals("b"));
		Ntro.asserter().assertTrue("get(9) == bb", sorted.get(9).equals("bb"));
	}

}
