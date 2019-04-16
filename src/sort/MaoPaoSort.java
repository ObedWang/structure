package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author :web
 * @date : 2019/4/16
 */
public class MaoPaoSort {

    /**
     * 冒泡排序
     *
     * @param array need to sort array
     */
    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 7, 8, 6, 5, 4, 1, 2, 3};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
                                                  