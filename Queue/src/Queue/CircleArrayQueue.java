package Queue;

import java.util.Scanner;

public class CircleArrayQueue {
    //环形数组队列
    static class CircleQueue {
        private int[] arr;  // 数组模拟队列
        private int maxSize;  //队列最大容量
        private int front;  //队列头
        private int rear;  //队列尾

        public CircleQueue(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new int[maxSize];
            this.front = 0; //0，指向数据头部元素，arr[front]
            this.rear = 0; //rear指向队列最后一个元素的后一个位置，也就是直接指向空位。
        }

        //判断队列是否满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
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
            //直接将数据加入
            arr[rear] = n;
            //将rear后移,必须取模,为了表现出环形效果,也防止index溢出.
            rear = (rear + 1) % maxSize;
        }

        //数据出列
        public int getQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                //通过抛出异常来处理，不能反回-1，因为有可能数据就是-1
                throw new RuntimeException("队列空，不能取数据");  //抛异常后直接返回，因此不用return
            }
            //需要分析front是否是指向队里的第一个元素
            //1. 将front对应的值保存到临时变量
            //2. 将front后移,需要取模
            //3. 将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //数组有效元素个数,因为rear可能front前面，需要加上maxSize
        public int getSize() {
            return (rear + maxSize - front) % maxSize;
        }

        //显示队列所有数据
        public void listQueue() {
            //遍历数组
            if (isEmpty()) {
                System.out.println("队列空");
                return;
            }
            //从front开始遍历，
            for (int i = front; i < front+getSize(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i %maxSize]);
            }
        }

        //显示队列头数据，不是取出数据
        public int headQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                throw new RuntimeException("空队列，无数据");
            }
            return arr[front];
        }
    }

    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(5); //实际只能存4个，因为我们预留了一个空位
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

