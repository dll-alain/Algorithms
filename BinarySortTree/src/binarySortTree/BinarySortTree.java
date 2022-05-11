package binarySortTree;

/**
 * @author dll
 * @date 20220427
 */
public class BinarySortTree {
    private Node root;

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回以node为根节点的二叉排序树的最小节点的值
     * 删除node为根节点的二叉树的最小节点
     *
     * @param node 传入二叉树的根节点
     * @return 放回以node为根节点的最小节点的值
     */
    public int deleteRightTreeMin(Node node) {
        //循环向左就能找到二叉树的最小值
        Node temp = node;
        while (temp.getLeft() != null) {
            temp.setLeft(temp.getLeft().getLeft());
        }
        deleteNode(temp.getValue());
        return temp.getValue();
    }

    public void deleteNode(int value) {
        if (root == null) {
            System.out.println("二叉树为空，无需删除");
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                System.out.println("输入数据错误");
                return;
            }
            if (root.getLeft() == null && root.getRight() == null) {
                //只有一个节点,没有父节点
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //1 目标节点是叶子节点
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                //判断目标节点是父节点的左/右 节点
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                    parent.setLeft(null);
                } else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                    parent.setRight(null);
                }
            } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                //2 目标节有点右两个子树
                int minValue = deleteRightTreeMin(targetNode.getRight());
                if (parent != null) {
                    targetNode.setValue(minValue);
                } else {
                    root.setValue(minValue);
                }
            } else {
                //3 目标节点有一个子树
                if (targetNode.getLeft() != null) {
                    //目标节点的唯一子树是左子节点
                    if (parent != null) {
                        if (parent.getLeft().getValue() == value) {
                            //如果目标节点是父节点的左节点
                            parent.setLeft(targetNode.getLeft());
                        } else {
                            //目标节点是父节点右节点
                            parent.setRight(targetNode.getLeft());
                        }
                    } else {
                        root = targetNode.getLeft();
                    }
                } else {
                    //目标节点的唯一节点是右子节点
                    if (parent != null) {
                        if (parent.getLeft().getValue() == value) {
                            parent.setLeft(targetNode.getRight());
                        } else {
                            parent.setRight(targetNode.getRight());
                        }
                    } else {
                        root = targetNode.getRight();
                    }
                }
            }
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public Node getRoot() {
        return root;
    }

    /**
     * 对binary sort tree 进行中序排序输出的直接是升序排列
     *
     * @param args sss
     */
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int j : arr) {
            binarySortTree.add(new Node(j));
        }

        binarySortTree.deleteNode(100);
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(12);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(1);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(2);


        System.out.println("infix遍历二叉树排序");
        binarySortTree.infixOrder();
    }
}
