package com.eason.coding.life.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	ClassLoader parent;

	public MyClassLoader(){
	}
	public MyClassLoader(ClassLoader parent) {
		this.parent = parent;
	}

	public Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		Class clazz = null;
		try {
			clazz = findLoadedClass(name); // 妫�煡璇ョ被鏄惁宸茬粡琚杞姐�
			if (clazz != null) {
				return clazz;
			}

			try {
				if (parent != null) {
					clazz = parent.loadClass(name);
				}
			} catch (ClassNotFoundException e) {
				// ClassNotFoundException thrown if class not found
				// from the non-null parent class loader
			}
			if(clazz == null){
				clazz = findClass(name, clazz);
			}
			if (clazz == null) { // 濡傛灉璇诲彇瀛楄妭澶辫触锛屽垯璇曞浘浠嶫DK鐨勭郴缁烝PI涓鎵捐绫汇�
				clazz = findSystemClass(name);
			}
			if (resolve && clazz != null) {
				resolveClass(clazz);
			}
		} catch (IOException e) {
			throw new ClassNotFoundException(e.toString());
		}
		System.out.println("clazz == " + clazz);
		return clazz;
	}

	private Class findClass(String name, Class clazz) throws IOException,
			ClassFormatError {
		byte[] bs = getClassBytes(name);// 浠庝竴涓壒瀹氱殑淇℃伅婧愬鎵惧苟璇诲彇璇ョ被鐨勫瓧鑺傘�
		if (bs != null && bs.length > 0) {
			clazz = defineClass(name, bs, 0, bs.length);
		}
		return clazz;
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException {
		return this.loadClass(name, false);
	}

	private byte[] getClassBytes(String className) throws IOException {
		String path = this.getClass().getResource("/").getPath();
		path += className.replace('.', File.separatorChar) + ".class";
		System.out.println(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return null; // 濡傛灉鏌ユ壘澶辫触锛屽垯鏀惧純鏌ユ壘銆傛崟鎹夎繖涓紓甯镐富瑕佹槸涓轰簡杩囨护JDK鐨勭郴缁烝PI銆�
		}
		byte[] bs = new byte[fis.available()];
		fis.read(bs);
		return bs;
	}
}
