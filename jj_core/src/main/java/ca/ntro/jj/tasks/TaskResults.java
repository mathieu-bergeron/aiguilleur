package ca.ntro.jj.tasks;


public interface TaskResults {

	<R extends Object> R getResult(Class<R> resultClass);

}
