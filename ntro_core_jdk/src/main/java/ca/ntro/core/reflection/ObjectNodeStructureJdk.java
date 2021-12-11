package ca.ntro.core.reflection;

import java.lang.reflect.Method;

import ca.ntro.core.reflection.object_graph.ObjectGraphNtro;
import ca.ntro.core.reflection.object_graph.ObjectNodeNtro;
import ca.ntro.core.reflection.object_graph.ObjectNodeStructureNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ObjectNodeStructureJdk extends ObjectNodeStructureNtro {

	public ObjectNodeStructureJdk(ObjectNodeNtro node, ObjectGraphNtro graph) {
		super(node, graph);
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
	protected Stream<String> methodNames(Object object) {
		return new StreamNtro<String>() {

			@Override
			public void _forEach(Visitor<String> visitor) throws Throwable {
				for(Method method : object.getClass().getMethods()) {

					visitor.visit(method.getName());
				}
			}
		};
	}

}
