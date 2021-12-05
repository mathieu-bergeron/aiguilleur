package ca.ntro.core.reflection;

import java.lang.reflect.Method;

import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ObjectNodeStructureNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ObjectNodeStructureJdk extends ObjectNodeStructureNtro {

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
	public ObjectNode asNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectGraph parentGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
