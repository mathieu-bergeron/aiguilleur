package ca.ntro.jj.tasks.results;


public interface NamedResults {

	<R extends Object> R getResult(Class<R> resultClass, String resultName);

}
