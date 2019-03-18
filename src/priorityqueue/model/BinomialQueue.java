package priorityqueue.model;

/**
 * @author :web
 * @date : 2019/3/14
 */
public class BinomialQueue<T extends Comparable<? super T>> {

    private static final int DEFAULT_TREES = 1;
    /**
     * items in priority queue
     */
    private int currentSize;
    /**
     * an array of tree roots
     */
    private Node<T>[] theTrees;

    @SuppressWarnings("unchecked")
    public BinomialQueue() {
        theTrees = new Node[DEFAULT_TREES];
        currentSize = 0;
    }

    @SuppressWarnings("unchecked")
    public BinomialQueue(T item) {
        theTrees = new Node[]{
                new Node(item), null
        };
        currentSize = 1;
    }

    /**
     * 将rhs合并到当前的优先队列
     * rhs变为空,rhs必须与当前的优先队列不一样
     *
     * @param rhs 另外一个二项队列
     */
    public void merge(BinomialQueue<T> rhs) {
        //避免混叠问题
        if (this == rhs)
            return;
        currentSize += rhs.currentSize;
        if (currentSize > capacity()) {
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTree(maxLength + 1);
        }
        Node<T> carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            Node<T> t1 = theTrees[i];
            Node<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;
            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;
            switch (whichCase) {
                /*No trees */
                case 0:
                    break;
                /* Only this */
                case 1:
                    break;
                // only rhs
                case 2:
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                // Only carry
                case 4:
                    theTrees[i] = carry;
                    carry = null;
                    break;
                //this and rhs
                case 3:
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                // this and carry
                case 5:
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                //rhs and carry
                case 6:
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                //all tree
                case 7:
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
                default:
                    break;
            }
        }
        //help gc
        for (int k = 0; k < rhs.theTrees.length; k++) {
            rhs.theTrees[k] = null;
        }
        rhs.currentSize = 0;
    }

    public void insert(T x) {
        merge(new BinomialQueue<>(x));
    }

    public T findMin() {
        return theTrees[findMinIndex()].element;
    }

    /**
     * 删除最小的元素
     *
     * @return 最小的元素, 如果队列为空则抛出异常
     */
    public T deleteMin() {
        if (isEmpty())
            throw new NullPointerException();
        int minIndex = findMinIndex();
        T min = theTrees[minIndex].element;
        Node<T> deletedTree = theTrees[minIndex].leftChild;

        BinomialQueue<T> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTree(minIndex + 1);
        deletedQueue.currentSize = (1 << minIndex) - 1;
        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;
        merge(deletedQueue);
        return min;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        theTrees = null;
    }

    /**
     * 对存储队列的树进行扩容
     *
     * @param newNumTrees 扩容大小
     */
    @SuppressWarnings("unchecked")
    private void expandTheTree(int newNumTrees) {
        Node[] newTrees = new Node[newNumTrees];
        System.arraycopy(theTrees, 0, newTrees, 0, theTrees.length);
        theTrees = newTrees;
    }

    /**
     * @param t1 t1
     * @param t2 t2
     * @return new Tree
     */
    private Node<T> combineTrees(Node<T> t1, Node<T> t2) {
        if (t1.element.compareTo(t2.element) > 0)
            return combineTrees(t2, t1);
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private int capacity() {
        return (1 << theTrees.length - 1);
    }

    private int findMinIndex() {
        int min = 0;
        T element = theTrees[0].element;
        for (int i = 1; i < theTrees.length; i++) {
            if (element.compareTo(theTrees[i].element) > 0) {
                element = theTrees[i].element;
                min = i;
            }
        }
        return min;
    }

    private static class Node<T> {
        /**
         * the data in the node
         */
        T element;
        /**
         * left child 左子节点 leftChild.element> this.element
         */
        Node<T> leftChild;
        /**
         * right child 平级的兄弟节点,大小任意
         */
        Node<T> nextSibling;

        public Node(T element, Node<T> leftChild, Node<T> nextSibling) {
            this.element = element;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }

        public Node(T element) {
            this.element = element;
        }
    }


}
                                                  