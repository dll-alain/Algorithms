package binarySortTree;

/**
 * @author dll
 */
public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找目标删除节点
     * @param value 希望删除的节点的值
     * @return 返回该节点
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //左子树查找, 需要判断左子节点是否为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            //查找的值不小于当前节点
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找目标节点的夫节点
     * @param value 目标值
     * @return 放回目标节点的父节点
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                //向左递归查找
               return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右递归
                return this.right.searchParent(value);
            } else {
                //没有父节点
                return null;
            }
        }
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.getValue() < this.value) {
            if (this.left == null) {
                this.setLeft(node);
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.setRight(node);
            } else {
                this.right.add(node);
            }
        }

        //avl tree 左旋 右子树的高度大于左子树的高度
        if (this.rightHeight() - this.leftHeight() > 1) {
            if (this.right != null && this.right.rightHeight() < this.right.leftHeight()) {
                //当右子树高度大于左子树的高度是，要保证下面的右子树亦然大于左子树
                //如果不是，就需要进行右旋，降低下面左子树的高度，增加右子树的高度
                this.right.rightRotate();
            }
            this.leftRotate();
            //选择完成后，对于这个节点，已经不要其他的选择操作，因此return
            return;
        }

        //avl 右旋, 左子树的高度大于右子树的高度
        if (this.leftHeight() - this.rightHeight() > 1) {
            if (this.left != null && this.left.leftHeight() < this.left.rightHeight()) {
                //当左子树高度大于右子树时，要保证下面的左子树高度亦然大于右子树
                //对左子树进行左旋，增加左子树的高度，降低右子树的高度
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }




    /**
     * for AVL tree
     * @return 放回以当前节点为根子树的高度,也就是为什么要加一
     */
    public int getHeight() {
        return Math.max(this.getLeft() == null ? 0 : this.getLeft().getHeight(), this.getRight() == null ? 0 : this.getRight().getHeight()) + 1;
    }

    /**
     * @return 放回当前节点的左子树的高度
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.getHeight();
    }

    /**
     * @return 放回当前节点的右子树的高度
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.getHeight();
    }

    /**
     * 左旋
     */
    private void leftRotate() {
        //1 创建新的节点，以当前根节点的值
        Node newNode = new Node(this.value);
        //2 新的节点的左子树指向根节点左子树
        newNode.setLeft(this.getLeft());
        //3 新节点的右子树指向根节点的右子树的左子树
        newNode.setRight(this.getRight().getLeft());
        //4 当前 根节点 的值 改为原来根节点右子树的值
        this.value = this.getRight().getValue();
        //5 把根节点右子树指向 根节点右子树的右子树
        this.setRight(this.getRight().getRight());
        //6 把根节点的左子树指向新的节点
        this.setLeft(newNode);
    }

    private void rightRotate() {
        Node newNode = new Node(this.getValue());
        //新的节点的右子树指向根节点的右子树
        newNode.setRight(this.getRight());
        //新节点的左子树指向根节点左子树的右子树
        newNode.setLeft(this.getLeft().getRight());
        //当前根节点的值改为根节点的左子树的值
        this.value = this.getLeft().getValue();
        //根节点的左子树指向 根节点的左子树的左子树
        this.setLeft(this.getLeft().getLeft());
        //根节点的右子树指向新节点
        this.setRight(newNode);
    }



    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
