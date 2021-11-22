package ca.ntro.core.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.bson.types.BasicBSONList;

public class JsonListJdk extends JsonListNtro implements JsonList {
	
	private BasicBSONList bsonList = new BasicBSONList();

	@Override
	public int size() {
		return bsonList.size();
	}

	@Override
	public boolean isEmpty() {
		return bsonList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return bsonList.contains(o);
	}

	@Override
	public Iterator<JsonValue<?>> iterator() {
		return new Iterator<JsonValue<?>>() {
			
			private Iterator<Object> iterator = bsonList.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public JsonValue<?> next() {
				return (JsonValue<?>) iterator.next();
			}
		};
	}

	@Override
	public Object[] toArray() {
		return bsonList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return bsonList.toArray(a);
	}

	@Override
	public boolean add(JsonValue<?> e) {
		return bsonList.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return bsonList.remove(o);
				
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return bsonList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends JsonValue<?>> c) {
		return bsonList.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends JsonValue<?>> c) {
		return bsonList.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return bsonList.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return bsonList.retainAll(c);
	}

	@Override
	public void clear() {
		bsonList.clear();
	}

	@Override
	public JsonValue<?> get(int index) {
		return (JsonValue<?>) bsonList.get(index);
	}

	@Override
	public JsonValue<?> set(int index, JsonValue<?> element) {
		return (JsonValue<?>) bsonList.set(index, element);
	}

	@Override
	public void add(int index, JsonValue<?> element) {
		bsonList.add(index, element);
	}

	@Override
	public JsonValue<?> remove(int index) {
		return (JsonValue<?>) bsonList.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return bsonList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return bsonList.lastIndexOf(o);
	}

	@Override
	public ListIterator<JsonValue<?>> listIterator() {
		return new ListIterator<JsonValue<?>>() {
			
			private ListIterator<Object> iterator = bsonList.listIterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public JsonValue<?> next() {
				return (JsonValue<?>) iterator.next();
			}

			@Override
			public boolean hasPrevious() {
				return iterator.hasPrevious();
			}

			@Override
			public JsonValue<?> previous() {
				return (JsonValue<?>) iterator.previous();
			}

			@Override
			public int nextIndex() {
				return iterator.nextIndex();
			}

			@Override
			public int previousIndex() {
				return iterator.previousIndex();
			}

			@Override
			public void remove() {
				iterator.remove();
			}

			@Override
			public void set(JsonValue<?> e) {
				iterator.set(e);
			}

			@Override
			public void add(JsonValue<?> e) {
				iterator.add(e);
			}
		};
	}

	@Override
	public ListIterator<JsonValue<?>> listIterator(int index) {
		return new ListIterator<JsonValue<?>>() {
			
			private ListIterator<Object> iterator = bsonList.listIterator(index);

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public JsonValue<?> next() {
				return (JsonValue<?>) iterator.next();
			}

			@Override
			public boolean hasPrevious() {
				return iterator.hasPrevious();
			}

			@Override
			public JsonValue<?> previous() {
				return (JsonValue<?>) iterator.previous();
			}

			@Override
			public int nextIndex() {
				return iterator.nextIndex();
			}

			@Override
			public int previousIndex() {
				return iterator.previousIndex();
			}

			@Override
			public void remove() {
				iterator.remove();
			}

			@Override
			public void set(JsonValue<?> e) {
				iterator.set(e);
			}

			@Override
			public void add(JsonValue<?> e) {
				iterator.add(e);
			}
		};
	}

	@Override
	public List<JsonValue<?>> subList(int fromIndex, int toIndex) {
		List<JsonValue<?>> subList = new ArrayList<JsonValue<?>>();
		
		for(Object element : bsonList) {
			subList.add((JsonValue<?>) element);
		}
		
		return subList;
	}
}
