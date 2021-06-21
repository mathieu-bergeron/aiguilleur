package ca.ntro.jj.tasks.results;


public interface TaskResults {

	<R extends Object> R getResult(Class<R> resultClass);

}
