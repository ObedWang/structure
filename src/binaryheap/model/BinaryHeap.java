package binaryheap.model;

/**
 * 二叉堆(底层完全树)
 *
 * @author :web
 * @date : 2019/3/13
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    //默认大小
    private static final int DEFAULT_CAPACITY = 10;
    //堆中元素数
    private int currentSize;
    //堆数组,第一个元素为空
    private T[] array;


    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        array = (T[]) new Comparable[capacity];
        currentSize = 0;
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (T item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    /**
     * 为任意的一个数组建立一个排序的堆.时间复杂度O(n)
     */
    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    /**
     * 使用上虑实现插入,并保证树还是一棵完全树
     *
     * @param x 插入元素
     */
    public void insert(T x) {
        //扩容
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        //Percolate up 上虑
        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }
    @SuppressWarnings("unchecked")
    private void enlargeArray(int size) {
        T[] newArray = (T[]) new Comparable[size];
        for(int i=0;i<array.length;i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public T findMin() {
        return array[1];
    }

    public T deleteMin() {
        if (isEmpty())
            throw new NullPointerException();
        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    /**
     * 堆下滤的内部方法.
     *
     * @param hole 从何开始下滤的标志位
     */
    private void percolateDown(int hole) {
        int child;
        T temp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            //首先先判断是否还有下一个节点
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(temp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = temp;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        array = null;
    }

}
                                                  