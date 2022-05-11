package ArrayStack;

/**
 * @author dll
 * @date 20220126
 */
public class ArrayStack {
    private int maxSize;
    /**数组模拟模拟栈*/
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        //数组需要初始化才能存数据
        stack = new int[this.maxSize];
    }


    /**
     * 栈满
     * @return isfull = true
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            //抛出异常本身就有终止，后面不需要加return
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     * 遍历时需要从栈顶开始显示数据
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }


}
