package ca.ntro.core.graphs.generics.graph;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.identifyers.Key;
import ca.ntro.core.path.Path;

public class GenericWalkNtro<E extends GenericStep, W extends GenericWalk<E,W>> implements GenericWalk<E,W> {
	
	private List<E> edges = new ArrayList<>();

	public GenericWalkNtro(){
	}
	
	public GenericWalkNtro(W walk){
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
	public W subWalk(int fromIndex) {
		return (W) new GenericWalkNtro<E,W>(edges.subList(fromIndex, edges.size()-1));
	}

}
