package HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dll
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = creatHuffmanTree(arr);
        precedingOrder(root);
    }

    /**
     * 1 遍历arr数组
     * 2 将每个arr元素构成一个node
     * 3 将每个arr放入到ArrayList中
     *
     * @param arr 传入初始数组
     * @return 放回创建好的Huffman树的根节点
     */
    public static Node creatHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //1 从小到大排序
            Collections.sort(nodes);
            //2 取出根节点权值最小的两个二叉树, 一个节点也是一个二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //3 新建一个二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4 ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5 parent 加入nodes中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void precedingOrder(Node root) {
        if (root != null) {
            root.precedingOrder();
        } else {
            System.out.println("是空树，无法遍历");
        }
    }

}

/**
 * 1 为了让Node节点之间进行比较,引入comparable接口
 * 2 以调用Collections类的sort方法，实现类之间的比较
 */
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void precedingOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.precedingOrder();
        }
        if (this.right != null) {
            this.right.precedingOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        //从小到大进行排序
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
