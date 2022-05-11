package SingleLinkedList;

import java.util.Stack;

/**
 * @author dll
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jerry");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }
}
