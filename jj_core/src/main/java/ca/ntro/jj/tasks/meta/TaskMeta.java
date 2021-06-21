package ca.ntro.jj.tasks.meta;

public interface TaskMeta<T extends TaskMeta<T,AT>, AT extends AtomicTaskMeta> {

	void addPreviousTask(T previousTask);
	void addEntryTask(AT entryTask);
	void addSubTask(T subTask);
	void addExitTask(AT exitTask);
	void addNextTask(T nextTask);

}
