package ca.ntro.core.graphs.generics.graph;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.path.Path;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

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

	@SuppressWarnings("unchecked")
	@Override
	public W subWalk(int fromIndex) {
		W subWalk = (W) Factory.newInstance(this.getClass());
		
		for(int i = fromIndex; i < edges.size(); i++) {
			subWalk.add(edges.get(i));
		}

		return (W) subWalk;
	}

	@Override
	public WalkId id() {
		WalkIdNtro id = new WalkIdNtro();
		
		edges().forEach(edge -> {
			id.add(new EdgeTypeNtro(Direction.FORWARD, edge.name()));
		});
		
		return (WalkId) id;
	}

	@Override
	public Stream<E> edges() {
		return new StreamNtro<E>() {
			@Override
			public void forEach_(Visitor<E> visitor) throws Throwable {
				for(E edge : edges) {
					visitor.visit(edge);
				}
			}
		};
	}

}
