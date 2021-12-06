package ca.ntro.core.graphs.generics.generic_graph;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.path.Path;

public class GenericWalkNtro<E extends GenericEdge> implements GenericWalk<E> {
	
	private List<E> edges = new ArrayList<>();

	public GenericWalkNtro(){
	}
	
	public GenericWalkNtro(GenericWalk<E> walk){
		for(int i = 0; i < walk.size(); i++) {
			add(walk.get(i));
		}
	}

	public GenericWalkNtro(List<E> edges){
		this.edges = edges;
	}

	@Override
	public int size() {
		return edges.size();
	}

	@Override
	public Key toKey() {
		Path path = Path.emptyPath();

		for(E edge : edges) {
			path.addName(edge.name().toString());
		}
		
		return new Key(path.toKey());
	}

	@Override
	public E get(int index) {
		return edges.get(index);
	}

	@Override
	public void add(E edge) {
		edges.add(edge);
	}

	@Override
	public boolean isEmpty() {
		return edges.isEmpty();
	}

	@Override
	public GenericWalk<E> subWalk(int fromIndex) {
		return new GenericWalkNtro<E>(edges.subList(fromIndex, edges.size()-1));
	}

}
