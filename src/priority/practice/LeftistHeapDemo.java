package priority.practice;

import priorityqueue.model.LeftistHeap;

/**
 * @author :web
 * @date : 2019/3/14
 */
public class LeftistHeapDemo {
    public static void main(String[] args) {
        LeftistHeap<Integer> leftistHeap1 = new LeftistHeap<>();
        LeftistHeap<Integer> leftistHeap2 = new LeftistHeap<>();
        for(int i = 1;i<5;i++){
            leftistHeap1.insert(i);
            leftistHeap2.insert(i+5);
        }
        leftistHeap1.merge(leftistHeap2);
        System.out.println(leftistHeap2.isEmpty());
        System.out.println(leftistHeap1.isEmpty());
        while(!leftistHeap1.isEmpty()){
            System.out.println(leftistHeap1.deleteMin());
        }

    }
}
                                                  