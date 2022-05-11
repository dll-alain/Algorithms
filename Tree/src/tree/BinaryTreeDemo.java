package tree;

/**
 * @author dll
 * @date 20220407
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        //先手动创建二叉树，后面再以递归创建二叉树
        HeroNode root = new HeroNode(1,"送姜");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"零充");
        HeroNode node5 = new HeroNode(5,"关圣");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序查找");
        HeroNode resNode1 = binaryTree.preOrderSearch(5);
        System.out.println("找到" + resNode1.getNo() + "号节点, 名叫 "+ resNode1.getName());

        System.out.println("中序查找");
        HeroNode resNode2 = binaryTree.infixOrderSearch(5);
        System.out.println("找到" + resNode2.getNo() + "号节点, 名叫 "+ resNode2.getName());

        System.out.println("后序查找");
        HeroNode resNode3 = binaryTree.postOrderSearch(5);
        System.out.println("找到" + resNode3.getNo() + "号节点, 名叫 "+ resNode3.getName());

        System.out.println("");
        binaryTree.preOrder();
        binaryTree.deleteNode(5);
        System.out.println("删除后");
        binaryTree.preOrder();

    }
}
