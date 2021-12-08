package ca.ntro.core.stream;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.wrappers.result.ResultNtro;

public class StreamTests {
	
	protected ExceptionThrowerMock registerMockExceptionThrower() {
		ExceptionThrowerMock exceptionThrowerMock = new ExceptionThrowerMock();

		InitializerTest.registerExceptionThrower(exceptionThrowerMock);

		return exceptionThrowerMock;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
	}
	
	private <V extends Object> Stream<V> createStream(V[] values){
		return new ArrayStream<V>(values);
	}
	
	@Test
	public void ifSome01() {
		Stream<Character> stream = createStream(new Character[] {'a','b','c','d'});
		
		Ntro.asserter().assertTrue("Should contain d", stream.ifSome(c -> c == 'd'));
		Ntro.asserter().assertFalse("Should not contain e", stream.ifSome(c -> c == 'e'));
	}

	@Test
	public void ifAll01() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3,4,5});
		
		Ntro.asserter().assertTrue("All lower than 6", stream.ifAll(i -> i < 6));
	}

	@Test
	public void map01() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3});
		
		Stream<Integer> doubles = stream.map(i -> i*2);
		
		List<Integer> doublesList = doubles.collect();
		
		Ntro.asserter().assertTrue("doubles[0] == 2", doublesList.get(0).equals(2));
		Ntro.asserter().assertTrue("doubles[1] == 4", doublesList.get(1).equals(4));
		Ntro.asserter().assertTrue("doubles[2] == 6", doublesList.get(2).equals(6));
	}

	@Test
	public void map02() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3});
		
		Stream<Character> chars = stream.map(i -> (char) (((int)'a') + i));
		
		List<Character> charList = chars.collect();
		
		Ntro.asserter().assertTrue("chars[0] == b", charList.get(0).equals('b'));
		Ntro.asserter().assertTrue("chars[1] == c", charList.get(1).equals('c'));
		Ntro.asserter().assertTrue("chars[2] == d", charList.get(2).equals('d'));
	}

	@Test
	public void reduceToValue01() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3});
		
		Integer sum = stream.reduce(0, (acc, i) -> {

			return acc+i;

		}).value();
		
		Ntro.asserter().assertEquals(sum, 6);
	}

	@Test
	public void reduceToStream01() {
		char[][] chars = new char[3][];
		chars[0] = new char[] {'a','b'};
		chars[1] = new char[] {'c','d'};
		chars[2] = new char[] {'e','f'};
		
		Stream<Integer> intStream = createStream(new Integer[] {0,1,2});
		
		//Stream<Character> charStream = intStream.reduce(initialValue, reducer)
	}
	
}
