package CircuitLinkedList;

/**
 * @author d
 */
public class Joseph {

    /**
     * 约瑟夫环
     * @param starNode 从第几号节点开始
     * @param k 数几下
     *
     */
    public static void countNode(CircleSingleLinkedList circleSingleLinkedList, int starNode, int k) {
        if (circleSingleLinkedList.getFirst() == null || starNode < 1 || starNode > circleSingleLinkedList.getNum()) {
            System.out.println("参数输入有误");
            return;
        }
        //让helper移动到first后
        CircuitNode helper = circleSingleLinkedList.getFirst();
        while (true) {
            if (helper.getNext() == circleSingleLinkedList.getFirst()) {
                break;
            }
            helper = helper.getNext();
        }
        //移动到指定节点
        CircuitNode temp = circleSingleLinkedList.getFirst();
        for (int i = 0; i < starNode -1; i++) {
            temp = temp.getNext();
            helper = helper.getNext();
        }
        //移动出圈,循环直到圈中只有一个节点
        while (true) {
            if (helper == temp) {
                //此时圈中只有一个节点
                break;
            }
            //让helper和temp移动k-1次
            for (int i = 0; i < k - 1; i++) {
                temp = temp.getNext();
                helper = helper.getNext();
            }
            //first指向要出圈的节点
            System.out.println("节点" +  temp.getNo() + "出圈");
            temp = temp.getNext();
            helper.setNext(temp);
        }
        System.out.println("最终留在圈中的节点编号为" + temp.getNo());
    }

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(25);
        //circleSingleLinkedList.showList();

        //Joseph环
        countNode(circleSingleLinkedList, 7, 7);
    }
}
