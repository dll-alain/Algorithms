package Calculator;

/**
 * @author d
 */
public class CalStack {
    private int maxSize;
    /**数组模拟模拟栈*/
    private int[] stack;
    private int top = -1;

    public CalStack(int maxSize) {
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

    /**
     * 优先级用数字表示
     * 数字越大，优先级越高
     * @param oper 算术字符，通过ASCII转化为int
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否是运算符
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        //用于存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default :
                System.out.println("错误运算符");
                break;
        }
        return res;
    }

    /**
     * 显示栈顶的值
     * 但是不出栈
     */
    public int peek() {
        return stack[top];
    }

}

