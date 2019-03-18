package priorityqueue.model;

/**
 * 左式堆
 * 底层使用链表的形式实现
 * @author :web
 * @date : 2019/3/14
 */
public class LeftistHeap<T extends Comparable<? super T>> {

    /**
     * root
     */
    private Node<T> root;

    public LeftistHeap() {
        root = null;
    }

    /**
     * 将rhs合并到优先队列中
     * rhs变为空.rhs需要与合并对象不同
     *
     * @param rhs 需要合并的左式堆
     */
    public void merge(LeftistHeap<T> rhs) {
        //避免混叠问题
        if (this == rhs)
            return;
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    /**
     * 插入一个新元素,当做两个左式堆合并来处理
     * @param x T
     */
    public void insert(T x) {
        root = merge(new Node<>(x),root);
    }

    public T findMin() {
        return root.element;
    }

    public T deleteMin() {
        if(isEmpty())
            throw new NullPointerException();
        T minItem = findMin();
        root = merge(root.left,root.right);
        return minItem;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    private static class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        /**
         * null path length 零路径长度
         */
        int npl;


        public Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            npl = 0;
        }

        public Node(T element) {
            this(element, null, null);
        }


    }

    /**
     * 用于合并两个root的内部方法
     * 处理异常情况,并递归调用merge1
     *
     * @param h1 h1
     * @param h2 h2
     * @return node
     */
    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        if (h1.element.compareTo(h2.element) < 0)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);
    }

    /**
     * 用于合并两个root的内部方法
     * 假定树不是空的,并且h1的root根包含最小项
     * @param h1 h1
     * @param h2 h2
     * @return node
     */
    private Node<T> merge1(Node<T> h1, Node<T> h2) {
        //单节点
        if(h1.left ==null)
            //h1节点中的其他字段已确定
            h1.left = h2;
        else{
            h1.right = merge(h1.right,h2);
            if(h1.left.npl<h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl+1;
        }
        return h1;
    }

    private void swapChildren(Node<T> t) {
        Node<T> tempNode = t.left;
        t.left = t.right;
        t.right = tempNode;
    }
}
                                                  