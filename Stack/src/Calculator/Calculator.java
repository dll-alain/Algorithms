package Calculator;


/**
 * @author dll
 * @date 20220126
 * @version 1.00
 */
public class Calculator {

    public static void main(String[] args) {


        String expression = "70*111/33+21*62-4*77";
        String keepNum = "";

        CalStack numStack = new CalStack(10);
        CalStack operStack = new CalStack(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';

        while (true) {
            ch = expression.substring(index, index+1).charAt(0);
            if (operStack.isOper(ch)) {
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，进行比较，当前的操作符优先级小于等于栈中的操作符，就需要从数栈中出栈两个数进行运算
                    //计算完，把当前的操作符如符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接如符号栈
                    operStack.push(ch);
                }
            } else {
                /**如果是数,则直接如数栈
                 * 这里需要注意，ch是字符1，要用ASCII转化为数字1，相差48
                 * 当处理多维数是，不能发现是一个数就立即入栈，有可能是多位数
                 * 需要想expression的表达式的index后再看一位，如果是数就进行烧苗，如果是符号才入栈.
                 * 需要定义一个字符串变量，用于拼接.
                */
                //这里体现了字符串的累加
                keepNum += ch;
                //如果ch已经是expression的最后一位,就直接入栈
                if (index == expression.length() -1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
                //keepNum是一个字符串，在入栈时需要转化为字符
                //判断下一个是不是数字
                //看后面一位，不是index++
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号,并运算
        while (true) {
            //符号栈为空便计算到最后
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        result = numStack.pop();
        System.out.println(expression + " = " + result);
    }


}
