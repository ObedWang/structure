package sort;

import java.util.Arrays;

/**
 * 选择排序 ->当遍历第i遍的时候将第i小的元素放到i的位置上
 * 时间复杂度
 * O(1/2*n^2)
 * @author :web
 * @date : 2019/4/16
 */
public class SelectSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    array[i] = array[i] ^ array[j];
                    array[j] = array[i] ^ array[j];
                    array[i] = array[i] ^ array[j];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 8, 4, 7, 5, 6, 1, 2, 3, 0};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
                                                  