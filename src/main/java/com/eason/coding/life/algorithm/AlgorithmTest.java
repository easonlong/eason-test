package com.eason.coding.life.algorithm;

import java.util.Arrays;

/**
 * Created by longyaokun on 2017/6/5.
 */
public class AlgorithmTest {

    public static void main(String[] args) {

        InsertSort insertSort = new InsertSort();
        SelectSort selectSort = new SelectSort();
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();
        int[] a = insertSort.array(10000);
        insertSort.test(Arrays.copyOf(a, a.length));
        selectSort.test(Arrays.copyOf(a, a.length));
        mergeSort.test(Arrays.copyOf(a, a.length));
        quickSort.test(Arrays.copyOf(a, a.length));

    }
}
