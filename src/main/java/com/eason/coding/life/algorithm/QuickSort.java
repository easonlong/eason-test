package com.eason.coding.life.algorithm;

/**
 * Created by longyaokun on 2017/6/7.
 */
public class QuickSort extends Sort {
    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int partitionIndex = partition(a, lo, hi);
        sort(a, lo, partitionIndex - 1);
        sort(a, partitionIndex + 1, hi);
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int partitionElem = a[lo];
        while (true) {
            //找到左边第一个大于partitionElem的元素的下标
            while (a[++i] < partitionElem) {
                if (i == hi) {
                    break;
                }
            }
            //找到右边第一个小于partitionElem的元素的下标
            while (a[--j] > partitionElem) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            //然后交换两个元素
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;

    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        sort.test();
    }

}
