package tree;

/**
 * @author dll
 */
public class ThreadedBinaryTree extends BinaryTree {

    public void infixThreadedNodes() {
        this.infixThreadedNodes(root);
    }

    public void proThreadedNotes() {
        this.proThreadedNotes(root);
    }

    public void proThreadedNotes(HeroNode node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;
        //这两个if非常关键
        if (node.getLeftType() == 0) {
            proThreadedNotes(node.getLeft());
        }
        if (node.getRightType() == 0) {
            proThreadedNotes(node.getRight());
        }
    }

    /**
     * 中序线索化化节点
     *
     * @param node
     */
    public void infixThreadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //1 线索化左子树
        infixThreadedNodes(node.getLeft());
        //2 线索化当前节点, 先处理当前节点的前驱节点 再处理当前
        if (node.getLeft() == null) {
            //当前节点的左指针为空, 左指针指向前一个节点
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        if (preNode != null && preNode.getRight() == null) {
            // 并的顺序很重要，必须先判断当前节点是否为空，再判断下一个节点是否为空
            //当前节点的右指针为空, 但是是从下一轮的当前节点的prenode来判断, 右指针指向后一个节点
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        // 每处理一个节点后，让当前节点是下一个节点的前驱节点
        preNode = node;
        // 3 线索化右子树
        infixThreadedNodes(node.getRight());
    }

    public void infixThreadedList() {
        //定义一个变脸，存储当前遍历节点，从root开始
        HeroNode node = root;
        while (node != null) {
            //循环找到leftType == 1 的节点，第一个找到的就是8节点
            //随后随着遍历而变化，因而当leftType == 1时，说明该节点是按照线索化
            //处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //到了第一个节点
            System.out.println(node);
            //向右
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历节点
            node = node.getRight();
        }
    }

    public void proThreadedList() {
        HeroNode node = root;
        while (node != null) {
            System.out.println(node);
            if (node.getLeftType() == 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
    }

    public void postThreaded() {
        this.postThreaded(root);
    }

    public void postThreaded(HeroNode node) {
        if (node == null) {
            return;
        }
        postThreaded(node.getLeft());
        postThreaded(node.getRight());
        if (node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;
    }

    public void postThreadedList() {
        HeroNode node = root;
        while (node != null && node.getLeftType() == 0) {
            node = node.getLeft();
        }
        while (node != null) {
            if (node.getRightType() == 1) {
                System.out.println(node);
                preNode = node;
                node = node.getRight();
            } else {
                if (node.getRight() == preNode) {
                    System.out.println(node);
                    if (node == root) {
                        return;
                    }
                    preNode = node;
                    //
                }
            }
        }

    }


    public static void main(String[] args) {
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();

        HeroNode root = new HeroNode(1, "送姜");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "零充");
        HeroNode node5 = new HeroNode(5, "关圣");
        HeroNode node6 = new HeroNode(6, "king");
        HeroNode node7 = new HeroNode(7, "godfury");
        HeroNode node8 = new HeroNode(8, "godwyn");
        HeroNode node9 = new HeroNode(9, "radagon");
        HeroNode node10 = new HeroNode(10, "rataen");
        HeroNode node11 = new HeroNode(11, "malika");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node4.setLeft(node8);
        node4.setRight(node9);
        node7.setLeft(node10);
        node7.setRight(node11);

        threadedBinaryTree.setRoot(root);

        //threadedBinaryTree.infixThreadedNodes();

        //测试10号节点
        HeroNode leftNode = node10.getLeft();
        HeroNode rightNode = node10.getRight();
        System.out.println("10号节点的中序节点是 = " + leftNode);
        System.out.println("10号节点的中序节点是 = " + rightNode);

        //System.out.println("使用线索化中序遍历");
        //8, 3, 10, 1, 14, 6
        //threadedBinaryTree.infixThreadedList();

        threadedBinaryTree.proThreadedNotes();
        System.out.println("使用线索化前序遍历");
        threadedBinaryTree.proThreadedList();
    }
}
