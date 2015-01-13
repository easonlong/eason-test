package com.eason.coding.life.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

public class Pair<F, S> implements Serializable {
	public F getKey() {
		return getFirst();
	}

	public S getValue() {
		return getSecond();
	}

	static final long serialVersionUID = 0;
	protected F first;
	protected S second;

	public Pair() {
	}

	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public S getSecond() {
		return second;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public int hashCode() {
		// TODO: consider eclipse implementation
		int x = first == null ? 0 : first.hashCode();
		int y = second == null ? 0 : second.hashCode();
		return ObjectUtils.hashCode(x, y);
	}

	public String toString() {
		return first + ":" + second;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		// TODO: consider eclipse implementation
		if (!(other instanceof Pair)) {
			return false;
		} else {
			Pair<F, S> otherPair = (Pair<F, S>) other;
			return ObjectUtils.equals(otherPair.first, first)
					&& ObjectUtils.equals(otherPair.second, second);
		}
	}

	public static <F, S> Pair<F, S> instanceOf(F f, S s) {
		return new Pair<F, S>(f, s);
	}

	public static <T> Collection<T> toCollection(Pair<T, T> pair) {
		List<T> pairList = new ArrayList<T>(2);
		pairList.add(pair.getFirst());
		pairList.add(pair.getSecond());
		return pairList;
	}

	public static <F, S> Pair<F, S> mapEntryToPair(Entry<F, S> entry) {
		return new Pair<F, S>(entry.getKey(), entry.getValue());
	}
}