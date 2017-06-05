package com.eason.coding.life.algorithm;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 选择排序算法
 * <p>
 * Created by longyaokun on 2017/6/5.
 */
public class SelectSort extends Sort{

    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = a[i]; // 最小的元素
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    swap(a, i, j);// 将最小的元素与第一个元素交换
                }
            }
        }
    }

    public static void main(String[] args) {
        SelectSort sort = new SelectSort();
        sort.test();
    }
}
