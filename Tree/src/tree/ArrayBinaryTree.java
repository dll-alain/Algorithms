package tree;

import java.lang.reflect.Array;

/**
 * @author d
 */
public class ArrayBinaryTree {
    /**
     * 存储数据节点的数组
     */
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 数组存储二叉树前序遍历
     *
     * @param index 数组下标
     */
    public void precedingOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            precedingOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            precedingOrder(2 * index + 2);
        }
    }

    /**
     * 重载precedingOrder
     */
    public void precedingOrder() {
        this.precedingOrder(0);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.precedingOrder();
    }

}


