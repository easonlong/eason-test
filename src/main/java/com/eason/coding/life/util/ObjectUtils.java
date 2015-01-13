package com.eason.coding.life.util;

import java.util.Collection;

public class ObjectUtils {
	public static boolean equals(Object x, Object y) {
		if (x == y)// pointer comparison
			return true;
		if (x == null || y == null)
			return x == y;
		else
			return x.equals(y);
	}

	// ============================================================================
	// Hash code generation utility methods start
	// ============================================================================
	/**
	 * Number used in hash code generation.
	 */
	protected static final int HASH_PRIME = 37;

	/**
	 * Return the hash code of the string.
	 *
	 * @param s
	 *            the <code>String</code> from which to expand the hashCode.
	 */
	public static int hashCode(String s) {
		return hashCode(0, s);
	}

	/**
	 * Expand an existing hash code by a string.
	 *
	 * @param s
	 *            the <code>String</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, String s) {
		if (s == null) {
			return hash;
		}

		return (hash * HASH_PRIME + s.hashCode());
	}

	/**
	 * Expand an existing hash code by a boolean.
	 *
	 * @param in
	 *            the <code>boolean</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, boolean in) {
		return hashCode(hash, (char) ((in) ? 1 : 0));
	}

	/**
	 * Expand an existing hash code by a byte.
	 *
	 * @param in
	 *            the <code>byte</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, byte in) {
		return hashCode(hash, (char) in);
	}

	/**
	 * Expand an existing hash code by a short.
	 *
	 * @param in
	 *            the <code>byte</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, short in) {
		return hashCode(hash, (char) in);
	}

	/**
	 * Expand an existing hash code by an integer.
	 *
	 * @param in
	 *            the <code>int</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, int in) {
		return hashCode(hash, (long) in);
	}

	/**
	 * Expand an existing hash code by a long.
	 *
	 * @param in
	 *            the <code>long</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, long in) {
		//
		// Long is 4 characters (8 bytes)
		//
		for (int i = 0; i < 4; i++, in >>>= 16) {
			hash = hashCode(hash, (char) in);
		}

		return hash;
	}

	/**
	 * Expand an existing hash code by a float.
	 *
	 * @param in
	 *            the <code>float</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, float in) {
		return hashCode(hash, (long) Float.floatToIntBits(in));
	}

	/**
	 * Expand an existing hash code by a double.
	 *
	 * @param in
	 *            the <code>double</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	public static int hashCode(int hash, double in) {
		return hashCode(hash, Double.doubleToLongBits(in));
	}

	/**
	 * Expand an existing hash code by an input character.
	 *
	 * @param in
	 *            the <code>char</code> from which to expand the hashCode.
	 * @param hash
	 *            the hashCode to expand.
	 * @return an expanded hashCode.
	 */
	protected static int hashCode(int hash, char in) {
		return (hash * HASH_PRIME + in);
	}

	public static int hashCode(int hash, Object o) {
		if (o == null)
			return hash;
		return hashCode(hash, o.hashCode());
	}

	public static int hashCode(Object[] array, int increment) {
		if (increment <= 0)
			increment = 1;
		int hash = 0;
		for (int i = 0; i < array.length; i += increment) {
			hash = hashCode(hash, array[i]);
		}
		return hash;
	}

	public static int hashCode(Object... array) {
		return hashCode(array, 0);
	}

	@SuppressWarnings("unchecked")
	public static int hashCode(Collection c) {
		// this is a bit random and can be tuned; the idea is that we should not
		// have to iterate over everything
		int increment = 5;
		if (c.size() == 0)
			return 0;
		if (c.size() < increment)
			increment = 1;
		return hashCode(c.toArray(), Math.min(c.size(), increment));
	}

}
