package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnResumeHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStartHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnStopHandler;
import ca.ntro.core.task_graphs.executable_task_graph.handlers.OnSuspendHandler;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;

public interface ExecutableAtomicTask extends AtomicTask<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {
	

	void onStart(OnStartHandler onStartHandler);
	void onSuspend(OnSuspendHandler onSuspendHandler);
	void onResume(OnResumeHandler onResumeHandler);
	void onStop(OnStopHandler onStopHandler);

}
