package ca.ntro.core.util;

import java.util.List;

import org.junit.Test;

import ca.ntro.core.initialization.Ntro;

public class SplitterTest {
	
	@Test
	public void testSplitter01() {
		
		List<String> segmentList = Splitter.split("seg01.seg02.seg03", ".");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Ntro.asserter().assertArrayEquals(new String[] {"seg01","seg02","seg03"}, segments);
	}

	@Test
	public void testSplitter02() {
		
		List<String> segmentList = Splitter.split("", "/");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Ntro.asserter().assertArrayEquals(new String[] {}, segments);
	}

	@Test
	public void testSplitter03() {
		
		List<String> segmentList = Splitter.split("/asdf", "/");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Ntro.asserter().assertArrayEquals(new String[] {"","asdf"}, segments);
	}

	@Test
	public void testSplitter04() {
		
		List<String> segmentList = Splitter.split("3fd+", "+");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Ntro.asserter().assertArrayEquals(new String[] {"3fd",""}, segments);
	}

	@Test
	public void testSplitter05() {
		
		List<String> segmentList = Splitter.split("3fd+&+asdf+&+123+&+", "+&+");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Ntro.asserter().assertArrayEquals(new String[] {"3fd","asdf","123",""}, segments);
	}

}
