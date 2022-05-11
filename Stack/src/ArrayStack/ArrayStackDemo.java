package ArrayStack;

import java.util.Scanner;

/**
 * @author dll
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(6);
        String key = "";
        boolean loop = true;
        Scanner in = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出栈");
            System.out.println("push: 压栈");
            System.out.println("pop:  出栈");
            System.out.println("请输入你的选择");
            key = in.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = in.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是" + res);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    break;
                case "exit":
                    in.close();
                    loop = false;
                    System.out.println("程序关闭");
                    break;
                default:
                    System.out.println("请输入正确的字符");
            }
        }
    }
}
