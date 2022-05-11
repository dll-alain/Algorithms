package SingleLinkedList;


import java.util.Stack;

/**
 * @author dll
 * @date 20220121
 */
public class SingleLinkedListDemo {

    /**
     * 获取单链表的节点个数
     * 不统计头结点
     * @param head 链表的头结点
     * @return 返回的就是有效节点数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            //空链表
            return 0;
        }
        int length = 0;
        //定义赋值变量
        HeroNode current = head.next;
        //指向next就没有统计头结点
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * 1. 编写一个方法，接受head节点，同时接受一个index
     * 2. index表示是倒数index个节点
     * 3. 先把链表从头到尾遍历，得到链表的总长度getlength
     * 4. 的到size后，我们从链表的第一个开始便到（size-index）个
     */

    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //链表为空，放回NULL
        if (head.next == null) {
            return null;
        }
        //第一次遍历的带链表长度
        int size = getLength(head);
        //第二次遍历size-index 位置
        //先校验index合理性
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量, for循环定位到index
        HeroNode current = head.next;
        for (int i = 0; i < size - index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 单链表反转
     * @param head
     */

    public static void reverseList(HeroNode head) {
        //如果是空链表或者只有一个节点则放回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义辅助指针
        HeroNode current = head.next;
        //指向当前节点的下一个节点
        HeroNode next = null;
        //反转头部临时节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来链表,完成反转
        while (current != null) {
            //暂时保存当前节点的下一个节点
            next = current.next;
            //让2连接到已经连接到reverse的1的前面
            current.next = reverseHead.next;
            //让2连接到reverse后面
            reverseHead.next = current;
            current = next;
        }
        //换头
        head.next = reverseHead.next;
    }

    /**
     * 利用栈数据结构来逆序打印链表
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode current = head.next;
        //将链表节点压入栈
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        //将栈中节点依次答应
        while (stack.size() > 0) {
            //栈先进后出
            System.out.println(stack.pop());
        }
    }

    /**
     * 行不通
     * 合并两个单链表
     * 短的链表并入长链表后面
     * @param head1
     * @param head2
     * @return
     //
    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        if (head1.next == null || head2.next == null) {
            return null;
        }
        int length1 = getLength(head1);
        int length2 = getLength(head2);
        if (length1 > length2) {
            HeroNode current1 = head1.next;
            while (current1.next != null) {
                current1 = current1.next;
            }
            HeroNode current2 = head2.next;
            while (current2.next != null) {
                current1.next = current2;
                current2 = current2.next;
                current1.next = current1.next.next;
            }
            return head1;
        } else {
            HeroNode current2 = head2.next;
            while (current2.next != null) {
                current2 = current2.next;
            }
            HeroNode current1 = head1.next;
            while (current1.next != null) {
                current2.next = current1;
                current1 = current1.next;
                current2.next = current2.next.next;
            }
            return head2;
        }
    }
    */


    /**
     * 合并两个链表
     * 关键是在把cur添加到一个链表末端之前，先把cur.next存起来,不然添加完后next就是null
     * @param singleLinkedList1
     * @param singleLinkedList2
     * @return 返回一个链表
     */

    public static SingleLinkedList merge(SingleLinkedList singleLinkedList1, SingleLinkedList singleLinkedList2) {
        HeroNode current = singleLinkedList2.getHead().next;
        while (current != null) {
            HeroNode temp = current.next;
            singleLinkedList1.addByOrder(current);
            current = temp;
        }
        return singleLinkedList1;
    }



    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "零充", "豹子头");
        HeroNode hero5 = new HeroNode(5, "沙砾", "冷酷杀神");
        HeroNode hero6 = new HeroNode(6, "亚托克斯", "达咩");
        HeroNode hero7 = new HeroNode(7, "佐伊", "暮光星灵");
        HeroNode hero8 = new HeroNode(8, "潘森", "哦哦哦");

        //添加链表节点
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();

        /**
         * 无序添加节点
         singleLinkedList.add(hero1);
         singleLinkedList.add(hero2);
         singleLinkedList.add(hero3);
         singleLinkedList.add(hero4);
         */

        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero5);

        singleLinkedList.list();

        //测修改节点信息
        HeroNode newhero4 = new HeroNode(4, "林冲", "爆炸头");
        singleLinkedList.update(newhero4);

        //显示
        System.out.println("--修改后显示--");
        singleLinkedList.list();

     /**
      * 删除节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        singleLinkedList.delete(2);
        // singleLinkedList.delete(3);
        System.out.println("--删除后的链表--");
        singleLinkedList.list();
      */

        //输出有效节点个数
        System.out.println("有效节点个数");
        System.out.println(getLength(singleLinkedList.getHead()));

        //输出倒数第二个节点
        System.out.println("res = " + findLastIndexNode(singleLinkedList.getHead(), 5));

        //反转
        System.out.println("反转链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        //逆序打印
        //不改变链表结构，逆序打印单链表
        System.out.println("用栈逆序答应单链表");
        reversePrint(singleLinkedList.getHead());

        /**
         * 行不通
        //合并链表
        System.out.println("合并链表");
        singleLinkedList1.addByOrder(hero7);
        singleLinkedList1.addByOrder(hero6);
        HeroNode newHead = mergeLinkedList(singleLinkedList1.getHead(), singleLinkedList.getHead());
        SingleLinkedList newLinkedList = new SingleLinkedList();
        HeroNode current = newHead.next;
        while (current.next != null) {
            newLinkedList.addByOrder(current);
            current = current.next;
        }
        newLinkedList.list();
         */

        //合并列表
        System.out.println("合并列表");
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(hero1);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero4);

        singleLinkedList1.addByOrder(hero6);
        singleLinkedList1.addByOrder(hero7);
        singleLinkedList1.addByOrder(hero8);
        singleLinkedList1.list();
        System.out.println("----");
        SingleLinkedList NLL = merge(singleLinkedList2, singleLinkedList1);
        NLL.list();


    }





}


