package com.eason.coding.life.algorithm;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.math.RandomUtils;

/**
 * 插入排序
 * <p>
 * Created by longyaokun on 2017/6/5.
 */
public class InsertSort extends Sort {

    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                swap(a, j - 1, j);
            }
        }
    }

    public static void main(String[] args) {
        InsertSort sort = new InsertSort();
        sort.test();
    }
}
