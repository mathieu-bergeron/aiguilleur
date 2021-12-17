package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnResumeHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStartHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnSuspendHandler;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface ExecutableAtomicTask extends AtomicTask<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {
	
	boolean start();
	boolean suspend();
	boolean resume();
	boolean stop();

	void onStart(OnStartHandler onStartHandler);

	void onSuspend(OnSuspendHandler onSuspendHandler);

	void onResume(OnResumeHandler onResumeHandler);

	void onStop(OnStopHandler onStopHandler);

}
