package com.eason.coding.life.reflect;

import java.lang.reflect.Method;

public class ReflectUtils {
	public static Object invokeMethod(Object onObject, String methodName,
			Object[] args, boolean includePrivateMethod)
			throws Exception {
		Class clazz = onObject.getClass();
		if (onObject instanceof Class)
			clazz = (Class) onObject;
		Method methods[] = clazz.getDeclaredMethods();
		Method bestFitMethod = null;
		for (int i = 0; i < methods.length; i++) {
			Class[] parameterTypes = methods[i].getParameterTypes();
			if (methods[i].getName().equals(methodName)
					&& isArgsFitTheParameterTypes(
							parameterTypes, args)) {
				bestFitMethod = methods[i];
				break;
			}
		}
		if (bestFitMethod == null) {
			throw new NoSuchMethodException(
					"No method with name: "
							+ methodName
							+ " which takes the follwing argument : "
							+ args
							+ " was found in "
							+ clazz);
		}
		if (includePrivateMethod) {
			bestFitMethod.setAccessible(true);
		}
		return bestFitMethod.invoke(onObject, args);
	}

	public static boolean isArgsFitTheParameterTypes(
			Class[] parameterTypes, Object[] args) {
		if (parameterTypes == null && args == null) {
			return true;
		}
		if (parameterTypes.length != args.length) {
			return false;
		}
		for (int i = 0; i < parameterTypes.length; i++) {
			if (!parameterTypes[i].isAssignableFrom(args[i]
					.getClass())) {
				return false;
			}
		}
		return true;
	}
}
