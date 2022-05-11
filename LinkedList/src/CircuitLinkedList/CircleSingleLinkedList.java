package CircuitLinkedList;

/**
 * @author dll
 */
public class CircleSingleLinkedList {

    /**
     * first节点，指向第一个不属于链表
     */
    private CircuitNode first = new CircuitNode(-1);

    /**
     * 添加节点，构成环形链表
     * @param num 要添加节点的数量
     */
    public void addNode(int num) {
        if (num < 1) {
            System.out.println("错误num");
            return;
        }
        //辅助指针帮助构建环形链表
        CircuitNode current = null;
        for (int i = 1; i <= num; i++) {
            //根据编号创建节点
            CircuitNode node = new CircuitNode(i);
            //如果是第一个
            if (i == 1) {
                first = node;
                first.setNext(first);
                //first不能动，辅助指针动
                current = first;
            } else {
                current.setNext(node);
                node.setNext(first);
                current = node;
            }
        }
    }

    /**
     * 打印环形链表
     */
    public void showList() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //first 不能动，需要构建一个辅助指针
        CircuitNode current = first;
        while (true) {
            //System.out.println("节点的编号 :" + current.getNo() + ".");
            System.out.println(current);
            if (current.getNext() == first) {
                //遍历完毕
                break;
            }
            current = current.getNext();
        }
    }

    public int getNum() {
        if (first == null) {
            System.out.println("链表为空");
        }
        CircuitNode current = first;
        int num = 1;
        while (true) {
            if (current.getNext() == first) {
                break;
            }
            current = current.getNext();
            num++;
        }
        return num;
    }

    public CircuitNode getFirst() {
        return first;
    }
}
