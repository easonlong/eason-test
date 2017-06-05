package com.eason.coding.life.algorithm;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.math.RandomUtils;

/**
 * Created by longyaokun on 2017/6/5.
 */
public abstract class Sort {

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public boolean verify(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int[] array(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = RandomUtils.nextInt(size);
        }
        return a;
    }

    public abstract void sort(int[] a);

    public void test() {
        int[] a = array(1000);
        sort(a);
        System.out.println(verify(a));
        System.out.println(ToStringBuilder.reflectionToString(a));
    }


}
