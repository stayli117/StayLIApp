package com.stayli.utils;


/**
 * 快排算法
 */
public class QuickSort {

    public static void qsort(int leftIndex, int rightIndex, int[] a) {
        if (leftIndex >= rightIndex) {
            return;
        }

        // 起始位置
        int start = leftIndex;
        // 结束位置
        int end = rightIndex;
        // 关键值 快排的速度快慢的关键因素
        int key = a[leftIndex];

        while (end > start) { // 第一次循环 区分关键值左右 两序列
            //从后往前比较 如果没有比关键值小的，
            // 比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
            while (end > start && a[end] >= key) {
                end--;
            }
            // 这段代码不要写到第二层循环中，以增加效率
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }

            //从前往后比较
            while (end > start && a[start] <= key) {//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > leftIndex) qsort(leftIndex, start - 1, a);//左边序列。第一个索引位置到关键值索引-1
        if (end < rightIndex) qsort(end + 1, rightIndex, a);//右边序列。从关键值索引+1到最后一个

    }

    public static void sort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition(array, lo, hi);



//        sort(array, lo, index - 1);
//        sort(array, index + 1, hi);
    }

    int [] arr = {2,6,4,3,1,7,0,9};
    //            0 6 4 3 1 7 6 9
    //
    //
    //
    //
    public static int partition(int[] array, int lo, int hi) {
        //固定的切分方式
        int key = array[lo];
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {//从后半部分向前扫描
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {//从前半部分向后扫描
                lo++;
            }
            array[hi] = array[lo];
            for (int i : array) {
                System.out.print(i);
            }
            System.out.println("");
        }
        array[hi] = key;
        for (int i : array) {
            System.out.print(i);
        }
        return hi;
    }


}
