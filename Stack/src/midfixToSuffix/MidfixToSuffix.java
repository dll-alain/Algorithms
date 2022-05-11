package midfixToSuffix;

import Calculator.ReversePolishNotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dll
 */
public class MidfixToSuffix {
    private String expression;

    public static int operation(String operation) {
        int add = 1;
        int sub = 1;
        int mul = 2;
        int div = 2;
        int result =0;
        switch (operation) {
            case "+":
                result = add;
                break;
            case "-":
                result = sub;
                break;
            case "*":
                result = mul;
                break;
            case "/":
                result = div;
                break;
            default:
                System.out.println("错误运算符");
                break;
        }
        return result;
    }

    public List<String> toInfixExpressionList(String string) {
        //把中缀表达式放入arraylist
        List<String> list = new ArrayList<>();
        int i = 0;
        String str; //多位数的拼接
        char ch;
        do {
            //如果ch是一个非数字，我门直接加入list
            if ((ch = string.charAt(i)) < 48 || (ch = string.charAt(i)) > 57) {
                //ASCII值在48和57之间不是数字
                list.add(""+ch);
                i++;
            } else {
                //如果是一个数就要考虑多位数问题
                str = "";
                //先将str置为空
                while (i < string.length() && (ch = string.charAt(i)) >= 48 && (ch = string.charAt(i)) <= 57) {
                    str += ch;
                    i++;
                }
                list.add(str);
            }
        } while (i < string.length());
        return list;
    }

    public List<String> parseMidfixToSuffix(List<String> list) {
        //符号栈
        Stack<String> s1 = new Stack<String>();
        //ArrayList 存储后缀
        ArrayList<String> s2 = new ArrayList<String>();

        for (String item: list) {
            //1 如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号也直接加入s1
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则异常弹出s1中的符号，直到遇到左括号
                //这一对括号需要丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //消除这一对括号
                s1.pop();
            } else {
                //item的优先级小于等于s1栈顶的运算符，将s1栈顶的运算符弹出加入s2
                //重复直到item优先级大于s1栈顶
                while (s1.size() != 0 && operation(s1.peek()) >= operation(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //这里string扫描完毕
        //将s1中剩余的运算符依次弹出加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
        //这里是list，list按顺序输出就相当于栈逆序输出。
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public static void main(String[] args) {
        MidfixToSuffix midfixToSuffix = new MidfixToSuffix();
        midfixToSuffix.setExpression("77*((66-99)+55)-14/6+4");
        List<String> ls = midfixToSuffix.toInfixExpressionList(midfixToSuffix.getExpression());
        System.out.println(ls);
        List<String> suffix = midfixToSuffix.parseMidfixToSuffix(ls);
        System.out.println("后缀表达式为：" + suffix);

        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        System.out.println("计算结果为：" + reversePolishNotation.calculate(suffix));


    }

}
