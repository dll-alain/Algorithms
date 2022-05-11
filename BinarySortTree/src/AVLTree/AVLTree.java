package AVLTree;

import binarySortTree.BinarySortTree;
import binarySortTree.Node;

/**
 * @author dll
 * @date 20220428
 */
public class AVLTree extends BinarySortTree {


    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int v : arr) {
            avlTree.add(new Node(v));
        }
        System.out.println("中根遍历");
        avlTree.infixOrder();
        System.out.println("平衡树的高度 = " + avlTree.getRoot().getHeight());
        System.out.println("平衡left树的高度 = " + avlTree.getRoot().leftHeight());
        System.out.println("平衡right树的高度 = " + avlTree.getRoot().rightHeight());

    }


}

