package com.eason.coding.life.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class ForkJoinTest {

	@Test
	public void test() throws Exception {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Future<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));

		Assert.assertEquals(new Integer(49995000), result.get());
	}

	@Test
	public void test2() throws Exception {
		int size = 1000;
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Random rnd = new Random();
		long[] array = new long[size];
		for (int i = 0; i < size; i++) {
			array[i] = rnd.nextInt();
		}
		forkJoinPool.submit(new SortTask(array));

		forkJoinPool.shutdown();
		forkJoinPool.awaitTermination(1000, TimeUnit.SECONDS);

		for (int i = 1; i < size; i++) {
			Assert.assertTrue(array[i - 1] < array[i]);
		}
	}

	class Calculator extends RecursiveTask<Integer> {

		private static final long serialVersionUID = 1L;
		private static final int THRESHOLD = 100;
		private int start;
		private int end;

		public Calculator(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			if ((start - end) < THRESHOLD) {
				for (int i = start; i < end; i++) {
					sum += i;
				}
			} else {
				int middle = (start + end) / 2;
				Calculator left = new Calculator(start, middle);
				Calculator right = new Calculator(middle + 1, end);
				left.fork();
				right.fork();

				sum = left.join() + right.join();
			}
			return sum;
		}

	}

	class SortTask extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		final long[] array;
		final int start;
		final int end;
		private int THRESHOLD = 100; // For demo only

		public SortTask(long[] array) {
			this.array = array;
			this.start = 0;
			this.end = array.length - 1;
		}

		public SortTask(long[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}

		protected void compute() {
			if (end - start < THRESHOLD)
				sequentiallySort(array, start, end);
			else {
				int pivot = partition(array, start, end);
				new SortTask(array, start, pivot - 1).fork();
				new SortTask(array, pivot + 1, end).fork();
			}
		}

		private int partition(long[] array, int start, int end) {
			long x = array[end];
			int i = start - 1;
			for (int j = start; j < end; j++) {
				if (array[j] <= x) {
					i++;
					swap(array, i, j);
				}
			}
			swap(array, i + 1, end);
			return i + 1;
		}

		private void swap(long[] array, int i, int j) {
			if (i != j) {
				long temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		private void sequentiallySort(long[] array, int lo, int hi) {
			Arrays.sort(array, lo, hi + 1);
		}
	}
}
