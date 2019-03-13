package binaryheap.practice;

import binaryheap.model.BinaryHeap;

/**
 * @author :web
 * @date : 2019/3/13
 */
public class Demo {
    public static void main(String[] args) {
        BinaryHeap<Integer> integerBinaryHeap = new BinaryHeap<>();
        for(int i=10;i>0;i--)
            integerBinaryHeap.insert(i);
        while(!integerBinaryHeap.isEmpty())
            System.out.println(integerBinaryHeap.deleteMin());

        System.out.println("---------------");
        Integer[] integers = new Integer[]{
                5,4,3,2,1
        };
        BinaryHeap<Integer> integerBinaryHeap1 = new BinaryHeap<>(integers);
        while(!integerBinaryHeap1.isEmpty())
            System.out.println(integerBinaryHeap1.deleteMin());
    }
}
                                                  