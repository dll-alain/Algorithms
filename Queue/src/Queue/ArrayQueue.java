package Queue;

import java.util.Scanner;

public class ArrayQueue {
    //数组队列
    static class ArrayQueue1 {
        private int[] arr;  // 数组模拟队列
        private int maxSize;  //队列最大容量
        private int front;  //队列头
        private int rear;  //队列尾

        public ArrayQueue1(int maxSize) {
            this.maxSize = maxSize;
            ArrayQueue1.this.arr = new int[maxSize];
            ArrayQueue1.this.front = -1; //-1，指向数据头部前一个位置
            ArrayQueue1.this.rear = -1; //指向队列尾部，就是队列最后一个数据
        }

        //判断队列是否满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        //判断队列是否空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n) {
            if (isFull()) {
                System.out.println("满了!!!");
                return;
            }
            rear++;  //raer后移
            arr[rear] = n;  //加数据从尾加
        }

        //数据出列
        public int getQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                //通过抛出异常来处理，不能反回-1，因为有可能数据就是-1
                throw new RuntimeException("队列空，不能取数据");  //抛异常后直接返回，因此不用return
            }
            front++;
            return arr[front]; //因为front指向队列前一个位置
        }

        //显示队列所有数据
        public void listQueue() {
            //遍历数组
            if (isEmpty()) {
                System.out.println("队列空");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }

        //显示队列头数据，不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                throw new RuntimeException("空队列，无数据");
            }
            return arr[front + 1];
        }
    }

    public static void main(String[] args) {
        ArrayQueue1 queue = new ArrayQueue1(5);
        char key = ' '; //接受用户输入
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("g(get): 得到队列数据");
            System.out.println("h(head): 显示头部信息");
            System.out.println("a(add): 添加信息到队列");
            key = in.next().charAt(0); //获得一个字符
            switch (key) {
                case 's':
                    queue.listQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数据");
                    queue.addQueue(in.nextInt());
                    break;
                case 'g':
                    try {
                        int res;
                        res = queue.getQueue();
                        System.out.println("取出的数据是：" + res);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int head;
                        head = queue.headQueue();
                        System.out.printf("数据头的数据是%d\n", head);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    break;
                case 'e':
                    in.close();
                    loop = false;
                    break;
                default:
                    System.out.println("请输入正确指令");
                    break;
            }
        }
        System.out.println("程序退出.");
    }

}
