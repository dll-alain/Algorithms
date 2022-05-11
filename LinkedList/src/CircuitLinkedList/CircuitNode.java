package CircuitLinkedList;

/**
 * @author dll
 */
public class CircuitNode {
    private int no;
    private CircuitNode next;

    public CircuitNode( int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }


    public CircuitNode getNext() {
        return next;
    }

    public void setNext(CircuitNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "CircuitNode{" +
                "no=" + no +
                '}';
    }
}
