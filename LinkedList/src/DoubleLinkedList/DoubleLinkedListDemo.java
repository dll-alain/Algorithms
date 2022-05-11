package DoubleLinkedList;

/**
 * @author dll
 * @date 20220124
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleHeroNode doubleHeroNode1 = new DoubleHeroNode(1, "艾瑞莉娅", "刀妹");
        DoubleHeroNode doubleHeroNode2 = new DoubleHeroNode(2, "卡托克斯", "剑魔");
        DoubleHeroNode doubleHeroNode3 = new DoubleHeroNode(3, "德莱尔斯", "诺手");
        DoubleHeroNode doubleHeroNode4 = new DoubleHeroNode(4, "莫德凯撒", "铁男");
        DoubleHeroNode doubleHeroNode5 = new DoubleHeroNode(5, "瑟提", "腕豪");

        DoubleLinkedList doubleLinkedList1 = new DoubleLinkedList();
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList();

        doubleLinkedList1.add(doubleHeroNode1);
        doubleLinkedList1.add(doubleHeroNode2);
        doubleLinkedList1.add(doubleHeroNode3);
        doubleLinkedList1.add(doubleHeroNode4);
        doubleLinkedList1.add(doubleHeroNode5);

        doubleLinkedList1.list();

        DoubleHeroNode doubleHeroNodeNew3 = new DoubleHeroNode(3, "泽丽", "花火");
        doubleLinkedList1.update(doubleHeroNodeNew3);
        System.out.println("修改后的");
        doubleLinkedList1.list();

        System.out.println("删除后的双链表");
        doubleLinkedList1.delete(3);
        doubleLinkedList1.list();

        System.out.println("按顺序添加");
        doubleLinkedList2.addByOrder(doubleHeroNode5);
        doubleLinkedList2.addByOrder(doubleHeroNode1);
        doubleLinkedList2.addByOrder(doubleHeroNode3);
        doubleLinkedList2.addByOrder(doubleHeroNode4);
        doubleLinkedList2.addByOrder(doubleHeroNode2);
        doubleLinkedList2.list();
    }
}
