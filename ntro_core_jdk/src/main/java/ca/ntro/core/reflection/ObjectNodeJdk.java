package ca.ntro.core.reflection;

import java.lang.reflect.Method;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.reflection.object_graph.LocalHeap;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsBuilder;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptionsBuilderNtro;
import ca.ntro.core.reflection.object_graph.ObjectNodeNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ObjectNodeJdk extends ObjectNodeNtro {

	public ObjectNodeJdk(LocalHeap localHeap, Object object, NodeId nodeId) {
		super(localHeap, object, nodeId);
	}

	@Override
	protected <R> void _reduceMethodNames(Object object, ResultNtro<R> result, MethodNameReducer<R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(Method method : object.getClass().getMethods()) {
			
			try {

				result.registerValue(reducer.reduceMethodName(result.value(), method.getName()));

			} catch (Throwable e) {
				
				result.registerException(e);
				break;
			}
		}
	}

	@Override
	protected Object invokeGetter(Object object, String getterName) throws Throwable {

		Method method = object.getClass().getMethod(getterName);

		Object returnValue = method.invoke(object);

		return returnValue;
	}

	@Override
	protected ObjectGraphSearchOptionsBuilder defaultSearchOptions() {
		return new ObjectGraphSearchOptionsBuilderNtro();
	}

}
