package Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dll
 * @date 20220128
 */
public class ReversePolishNotation {

    private String suffixExpression = "";

    public ReversePolishNotation() {

    }


    /**
     * 将逆波兰表达式放入arraylist中
     */
    public  List<String> getListSting(String suffixExpression) {
        //将suffix分割 安装空格分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    public int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        for (String item: ls) {
            //使用正则表达式取出数
            if (item.matches("[\\d]+")) {
                stack.push(item);
            } else {
                //pop出两个数并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (item) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("错误运算符");
                }
                //简单整数转为字符串.
                stack.push(""+result);
            }
        }
        return Integer.parseInt(stack.pop());
    }



    public void setSuffixExpression(String suffixExpression) {
        this.suffixExpression = suffixExpression;
    }

    public String getSuffixExpression() {
        return suffixExpression;
    }

    public static void main(String[] args) {
        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        reversePolishNotation.setSuffixExpression("4 5 * 8 - 60 + 8 2 / +");
        List<String> print = reversePolishNotation.getListSting(reversePolishNotation.getSuffixExpression());
        System.out.println("计算结果为: " + reversePolishNotation.calculate(print));
    }

}
