package RBTree;

import java.util.Scanner;

/**
 * @author d
 */
public class RBTDemo {

    public static void main(String[] args) {
        //insertOption();
        deleteOpt();
    }

    public static void insertOption() {
        Scanner scanner = new Scanner(System.in);
        RedBlackTree<String, Object> redBlackTree = new RedBlackTree<>();
        while (true) {
            System.out.println("请输入你要插入的节点：");
            String key = scanner.next();
            System.out.println();
            if (key.length() == 1) {
                key = "00" + key;
            } else if (key.length() == 2) {
                key = "0" + key;
            }
            redBlackTree.put(key, null);
            TreeOperation.show(redBlackTree.getRoot());
        }

    }

    public static void deleteOpt() {
        RedBlackTree<String, Object> rbt = new RedBlackTree<>();
        //测试2：包含2位数和3位数的测试代码 1 2 3 4 5 66 77 88 99 100 101
        rbt.put("001", null);
        rbt.put("002", null);
        rbt.put("003", null);
        rbt.put("004", null);
        rbt.put("005", null);
        rbt.put("006", null);
        rbt.put("007", null);
        rbt.put("008", null);
        rbt.put("009", null);
        rbt.put("010", null);
        rbt.put("011", null);
        rbt.put("012", null);
        rbt.put("013", null);
        rbt.put("014", null);
        rbt.put("015", null);
        rbt.put("016", null);
        rbt.put("017", null);
        rbt.put("018", null);
        rbt.put("019", null);
        rbt.put("020", null);
        rbt.put("021", null);

        TreeOperation.show(rbt.getRoot());
        //以下开始删除
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你要删除的节点:");
            String key = scanner.next();
            System.out.println();
            //这里代码最多支持3位数，3位以上的话红黑树显示太错位了，这里就不重构代码了,大家可自行重构
            if (key.length() == 1) {
                key = "00" + key;
            } else if (key.length() == 2) {
                key = "0" + key;
            }
            //1 2 3 88 66 77 100 5 4 101
            rbt.remove(key);
            TreeOperation.show(rbt.getRoot());
        }
    }
}
