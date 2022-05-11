package DoubleLinkedList;


/**
 * 双向链表
 * @author dll
 * @version 1.00
 * @date 20220124
 */
public class DoubleLinkedList {
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }

    public void list () {
        if (head.next == null) {
            return;
        }
        DoubleHeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(DoubleHeroNode doubleHeroNode) {
        DoubleHeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = doubleHeroNode;
        doubleHeroNode.pre = temp;
    }

    public void update(DoubleHeroNode doubleHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == doubleHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = doubleHeroNode.name;
            temp.nickname = doubleHeroNode.nickname;
        } else {
            System.out.println("没有找到");
        }
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        DoubleHeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //核心语句
            temp.pre.next = temp.next;
            //这里要考虑是否是最后一个节点
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有找到");
        }
    }

    /**
     * 按顺序添加双链表，还是很巧妙的
     * 关键在flag后面分为两段.
     * @param doubleHeroNode
     */

    public void addByOrder(DoubleHeroNode doubleHeroNode) {
        DoubleHeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > doubleHeroNode.no) {
                break;
            }
            if (temp.next.no == doubleHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("该节点已存在");
        } else {
            if (temp.next != null) {
                //如果没到尾端，插入节点需要与后面节点相连
                doubleHeroNode.next = temp.next;
                temp.next.pre = doubleHeroNode;
            }
            //无论是否到尾端，都需要 与前面节点相连
            temp.next = doubleHeroNode;
            doubleHeroNode.pre = temp;
        }
    }



}
