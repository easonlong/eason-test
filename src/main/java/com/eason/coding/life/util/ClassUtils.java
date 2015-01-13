package com.eason.coding.life.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

public class ClassUtils {
	private static Set<Class<?>> PRIMITIVES;
	private static Log logger = LogFactory.getLog("ClassUtils");
	static {
		initPrimitives();
	}

	private static void initPrimitives() {
		PRIMITIVES = new HashSet<Class<?>>();

		PRIMITIVES.add(int.class);
		PRIMITIVES.add(Integer.class);
		PRIMITIVES.add(short.class);
		PRIMITIVES.add(Short.class);
		PRIMITIVES.add(long.class);
		PRIMITIVES.add(Long.class);
		PRIMITIVES.add(double.class);
		PRIMITIVES.add(Double.class);
		PRIMITIVES.add(float.class);
		PRIMITIVES.add(Float.class);
		PRIMITIVES.add(java.lang.Object.class);
		PRIMITIVES.add(java.lang.String.class);
		PRIMITIVES.add(java.util.Date.class);
		PRIMITIVES.add(java.util.Calendar.class);
		PRIMITIVES.add(java.math.BigDecimal.class);
		PRIMITIVES.add(java.math.BigInteger.class);

		logger.debug("registered primitives are:  " + PRIMITIVES);
	}
	
	public static <T> Map<String, Object> compareObj(T o1, T o2,
			String prePath, boolean recurse) throws Exception {

		if (o1 == null || o2 == null) {
			return new HashMap<String, Object>();
		}
		Class<T> clazz = (Class<T>) o1.getClass();
		Field[] fields = clazz.getDeclaredFields();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (Field field : fields) {
			PropertyDescriptor d= BeanUtils.getPropertyDescriptor(clazz, field.getName());
			if(d==null){
				continue;
			}
			Method readMethod = d.getReadMethod();
			String currentPath = prePath + "." + d.getName();
			if (readMethod == null)
				continue;

			if (d.getWriteMethod() == null
					&& !d.getPropertyType().isAssignableFrom(List.class)){
				continue;
			}
			if (recurse && (!PRIMITIVES.contains(d.getPropertyType()))) {
				if (!Collection.class.isAssignableFrom((d.getPropertyType()))) {
					resultMap.putAll(compareObj(readMethod.invoke(o1),
							readMethod.invoke(o2), currentPath, true));
				} else {
					Object collection1 = d.getReadMethod().invoke(o1);
					Object collection2 = d.getReadMethod().invoke(o2);
					resultMap.putAll(compareCollection(collection1,
							collection2, currentPath));
				}
			} else {
				Object value1 = null;
				Object value2 = null;
				try {
					value1 = readMethod.invoke(o1);
					value2 = readMethod.invoke(o2);
				} catch (Exception npe) {

				}
				Pair<String, String> diffValue = comparePrimitives(value1,
						value2);
				if (diffValue != null) {
					resultMap.put(currentPath, diffValue);
				}
			}

		}

		return resultMap;

	}

	public static Pair<String, String> comparePrimitives(Object value1,
			Object value2) {
		if (value1 == null && value2 == null) {
			return null;
		} else if (value1 == null && value2 != null) {
			return new Pair<String, String>("null", String.valueOf(value2));
		} else if (value1 != null && value2 == null) {
			return new Pair<String, String>(String.valueOf(value1), "null");
		}
		String str1 = String.valueOf(value1);
		String str2 = String.valueOf(value2);

		if (str1.equals(str2)) {
			return null;
		} else {
			return new Pair<String, String>(str1, str2);
		}

	}

	public static <T> Map<String, Object> compareCollection(T o1, T o2,
			String path) throws Exception {
		if ((o1 instanceof List) && (o2 instanceof List)) {
			return compareList((List) o1, (List) o2, path);
		}
		return null;

	}
	
	public static Map<String, Object> compareList(List list1, List list2,
			String path) throws Exception {
		if (list1 == null || list2 == null)
			return null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int size1 = list1.size();
		int size2 = list2.size();
		for (int i = 0; i < Math.min(size1, size2); i++) {
			resultMap.putAll(compareObj(list1.get(i), list2.get(i), path + i,
					true));
		}
		if (size1 > size2) {
			for (int i = Math.min(size1, size2); i < Math.max(size1, size2); i++) {
				resultMap.put(path + i, new Pair<String, String>(path + i,"null"));
			}
		} else if (size1 < size2) {
			for (int i = Math.min(size1, size2); i < Math.max(size1, size2); i++) {
				resultMap.put(path + i, new Pair<String, String>("null",path + i));
			}
		}
		return resultMap;
	}
	
}
