package SingleLinkedList;

/**
 * @author dll
 * @date 20220121
 */
public class SingleLinkedList {
    /**
     * 先初始化头结点，不存放具体数据，头结点一般不动
    */
    private  HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //找到当前链表的最后节点，再将最后这个节点的next指向新的节点
        //应为head节点不能动，需要一个临时指针
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //当退出while时，temp指向了链表的最后
        temp.next = heroNode;
    }

    //显示链表,同意需要一个临时指针辅助遍历
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 按照排名添加节点，如果已经有相同排名，提示错误
     */
    public void addByOrder(HeroNode heroNode) {
        //新建辅助指针，找的是要插入的前一个节点
        HeroNode temp = head;
        // 标志添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                //插入位置就位temp的后面
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag == true) {
            System.out.printf("准备插入的编号 {%d} 已经存在, 不能添加\n",  heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点信息
     * 编号不能改，改了相当于添加节点
     * 根据newheronode的no来修改
     */
    public void update(HeroNode newheroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助变量
        HeroNode temp = head.next;
        //是否找到该要修改的节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //到了链表的最后,链表遍历结束
                break;
            }
            if (temp.no == newheroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newheroNode.name;
            temp.nickname = newheroNode.nickname;
        } else {
            System.out.println("没有找到编号为" + newheroNode.no + "的节点");
        }
    }

    /**
     * 删除节点
     * 也是找到前一个节点
     * 临时指针指向下下个节点
     */
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;

        while (true){
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除的节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点{%d}不存在", no);
        }
    }

    public HeroNode getHead() {
        return head;
    }

}
