package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;

public class AtomicTasksNodeSpecNtro 

       implements AtomicTasksNodeSpec {
	
	private Stream<GenericAtomicTask<?,?>> atomicTasks;


	public Stream<GenericAtomicTask<?, ?>> getAtomicTasks() {
		return atomicTasks;
	}

	public void setAtomicTasks(Stream<GenericAtomicTask<?, ?>> atomicTasks) {
		this.atomicTasks = atomicTasks;
	}

	public AtomicTasksNodeSpecNtro() {
	}

	public AtomicTasksNodeSpecNtro(Stream<GenericAtomicTask<?,?>> atomicTasks) {
		setAtomicTasks(atomicTasks);
	}

	@Override
	public String label() {
		StringBuilder builder = new StringBuilder();
		
		builder.append('{');

		getAtomicTasks().forEach(atomicTask -> {
			if(builder.length() > 0) {
				builder.append("|");
			}

			builder.append(atomicTask.id().toKey().toString());
		});

		builder.append('}');
		
		return builder.toString();
	}

	@Override
	public boolean isCluster() {
		return false;
	}

	@Override
	public String color() {
		return null;
	}

	@Override
	public String shape() {
		return "record";
	}

	@Override
	public String id() {
		StringBuilder builder = new StringBuilder();
		
		getAtomicTasks().forEach(atomicTask -> {
			if(builder.length() > 0) {
				builder.append("_");
			}

			builder.append(atomicTask.id().toKey().toString());
		});
		
		return builder.toString();
	}
}
