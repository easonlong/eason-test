package com.eason.coding.life.algorithm;

/**
 * Created by longyaokun on 2017/6/7.
 */
public class MergeSort extends Sort {

    private int[] temp;

    @Override
    public void sort(int[] a) {
        temp = new int[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 分成两个数组a[lo..mid],a[mid+1..hi]分别排序，然后归并
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            temp[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            // 如果左边的数组遍历完了则将右边数组的剩余元素依次加入归并后的数组即可
            if (i > mid) {
                a[k] = temp[j];
                j++;
                continue;
            }
            // 如果右边的数组遍历完了则将左边数组的剩余元素依次加入归并后的数组即可
            if (j > hi) {
                a[k] = temp[i];
                i++;
                continue;
            }

            if (temp[i] < temp[j]) {
                a[k] = temp[i];
                i++;
            } else {
                a[k] = temp[j];
                j++;
            }

        }

    }

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        sort.test();
    }
}
