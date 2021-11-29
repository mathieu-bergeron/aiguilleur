package ca.ntro.core.reflection;

public class ReflectionUtils {

	public static boolean isUserDefinedMethod(Object object, String methodName) {
		boolean isUserDefined = true;
		
		if(methodName.equals("getClass")) {

			isUserDefined = false;
			
		} else if(object instanceof String && 
				(methodName.equals("getChars")
						|| methodName.equals("getBytes"))) {
			
			isUserDefined = false;
		}

		return isUserDefined;
	}

	public static boolean isGetterName(String methodName) {
		return methodName.startsWith("get")
				&& methodName.length() > 3;
	}


	public static String attributeNameFromGetterName(String methodName) {

		String rawAttributeName = methodName.substring(3);
		String attributeName = rawAttributeName.substring(0,1).toLowerCase();
		attributeName += rawAttributeName.substring(1);
		
		return attributeName;
	}

	public static String getterNameFromAttributeName(String attributeName) {
		
		String getterName = "get";
		
		getterName += attributeName.substring(0,1).toUpperCase();
		getterName += attributeName.substring(1);
		
		return getterName;
	}
}
