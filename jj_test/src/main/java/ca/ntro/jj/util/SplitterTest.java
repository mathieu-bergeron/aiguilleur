package ca.ntro.jj.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SplitterTest {
	
	@Test
	public void testSplitter01() {
		
		List<String> segmentList = Splitter.split("seg01.seg02.seg03", ".");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Assert.assertArrayEquals(new String[] {"seg01","seg02","seg03"}, segments);
	}

	@Test
	public void testSplitter02() {
		
		List<String> segmentList = Splitter.split("", "/");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Assert.assertArrayEquals(new String[] {}, segments);
	}

	@Test
	public void testSplitter03() {
		
		List<String> segmentList = Splitter.split("/asdf", "/");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Assert.assertArrayEquals(new String[] {"","asdf"}, segments);
	}

	@Test
	public void testSplitter04() {
		
		List<String> segmentList = Splitter.split("3fd+", "+");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Assert.assertArrayEquals(new String[] {"3fd",""}, segments);
	}

	@Test
	public void testSplitter05() {
		
		List<String> segmentList = Splitter.split("3fd+&+asdf+&+123+&+", "+&+");
		
		String[] segments = segmentList.toArray(new String[segmentList.size()]);
		
		Assert.assertArrayEquals(new String[] {"3fd","asdf","123",""}, segments);
	}

}
