package ca.ntro.core.stream;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

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
	public void streamEmpty() {
		Stream<Character> stream = createStream(new Character[] {});
		
		Ntro.asserter().assertTrue("Is emtpy", stream.isEmpty());
	}

	@Test
	public void streamNotEmpty() {
		Stream<Character> stream = createStream(new Character[] {'a','b'});
		
		Ntro.asserter().assertFalse("Is not emtpy", stream.isEmpty());
	}

	@Test
	public void ifAll01() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3,4,5});
		
		Ntro.asserter().assertTrue("All lower than 6", stream.ifAll(i -> i < 6));
	}

	@Test
	public void get01() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3,4,5});
		
		Ntro.asserter().assertEquals(1, stream.get(0));
		Ntro.asserter().assertEquals(2, stream.get(1));
		Ntro.asserter().assertEquals(3, stream.get(2));
		Ntro.asserter().assertEquals(4, stream.get(3));
		Ntro.asserter().assertEquals(5, stream.get(4));
	}

	@Test
	public void manualCollect() {
		Stream<Integer> stream = createStream(new Integer[] {1,2,3,4,5});
		
		List<Integer> collected = new ArrayList<>();
		
		for(int i = 0; i < stream.size(); i++) {
			collected.add(stream.get(i));
		}
		
		Ntro.asserter().assertEquals(1, collected.get(0));
		Ntro.asserter().assertEquals(2, collected.get(1));
		Ntro.asserter().assertEquals(3, collected.get(2));
		Ntro.asserter().assertEquals(4, collected.get(3));
		Ntro.asserter().assertEquals(5, collected.get(4));
	}

	@Test
	public void indexOutOfBounds01() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		Stream<Integer> stream = createStream(new Integer[] {1,2,3,4,5});
		
		stream.get(-1);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(IndexOutOfStreamException.class));
	}

	@Test
	public void indexOutOfBounds02() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		Stream<Integer> stream = createStream(new Integer[] {1,2,3,4,5});
		
		stream.get(stream.size());

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(IndexOutOfStreamException.class));
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
		
		Integer sum = stream.reduceToResult(0, (acc, i) -> {

			return acc+i;

		}).value();
		
		Ntro.asserter().assertEquals(sum, 6);
	}

	@Test
	public void reduceToStream01() {
		
		Stream<Integer> intStream = createStream(new Integer[] {1,2});
		
		Stream<Integer> intStream2 = intStream.reduceToStream((item, visitor) -> {
			for(int i = 0; i < item; i++) {
				visitor.visit(i);
			}
		});
		
		List<Integer> intList = intStream2.collect();

		Ntro.asserter().assertEquals(intList.size(), 3);
		Ntro.asserter().assertTrue("chars[0] == 0", intList.get(0).equals(0));
		Ntro.asserter().assertTrue("chars[1] == 0", intList.get(1).equals(0));
		Ntro.asserter().assertTrue("chars[2] == 1", intList.get(2).equals(1));
	}

	@Test
	public void reduceToStream02() {
		char[][] chars = new char[3][];
		chars[0] = new char[] {'a','b'};
		chars[1] = new char[] {'c'};
		chars[2] = new char[] {'d','e','f'};
		
		Stream<Integer> intStream = createStream(new Integer[] {0,1,2});
		
		Stream<Character> charStream = intStream.reduceToStream((item, visitor) -> {

			char[] insideChars = chars[item];

			for(char insideChar : insideChars) {
				visitor.visit(insideChar);
			}
		});
		
		List<Character> charList = charStream.collect();

		Ntro.asserter().assertEquals(charList.size(), 6);
		Ntro.asserter().assertTrue("chars[0] == a", charList.get(0).equals('a'));
		Ntro.asserter().assertTrue("chars[1] == b", charList.get(1).equals('b'));
		Ntro.asserter().assertTrue("chars[2] == c", charList.get(2).equals('c'));
		Ntro.asserter().assertTrue("chars[3] == d", charList.get(3).equals('d'));
		Ntro.asserter().assertTrue("chars[4] == e", charList.get(4).equals('e'));
		Ntro.asserter().assertTrue("chars[5] == f", charList.get(5).equals('f'));
	}
	
	@Test
	public void streamException() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		Stream<Integer> intStream = createStream(new Integer[] {0,1,2,3,4,5});
		
		List<Integer> visited = new ArrayList<>();
		
		intStream.forEach(i -> {
			visited.add(i);
			if(i == 2) {
				throw new MockException();
			}
		});

		Ntro.asserter().assertEquals(3,visited.size());
		Ntro.asserter().assertTrue("Should throw", exceptionThrower.wasThrown(MockException.class));
	}

	@Test
	public void streamBreak() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		Stream<Integer> intStream = createStream(new Integer[] {0,1,2,3,4,5});
		
		List<Integer> visited = new ArrayList<>();
		
		intStream.forEach(i -> {
			visited.add(i);
			if(i == 2) {
				throw new Break();
			}
		});

		Ntro.asserter().assertEquals(3, visited.size());
		Ntro.asserter().assertTrue("Should not throw", !exceptionThrower.wasThrown(Break.class));
	}
	
	
}
