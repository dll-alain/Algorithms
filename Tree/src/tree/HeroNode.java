package tree;

/**
 * @author d
 */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    /**
     * 1 如果leftType == 0 表示指向左子树，如果1 表示指向前驱节点
     * 2 如果rightType == 0 表示指向右子树，如果1 表示指向后驱节点
     */
    private int leftType = 0;
    private int rightType = 0;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 1 如果删除的节点是叶子节点，则删除该节点
     * 2 如果删除的节点是非叶子节点，则删除该子树
     * @param no 要删除节点的编号
     */
    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            //1 如果当前节点左子点不为空，且就是要删除的节点
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            //2 如果当前节点右子点不为空，且就是要删除的节点
            this.right = null;
            return;
        }
        if (this.left != null) {
            //3 我们向左子树进行递归删除
            this.left.deleteNode(no);
        }
        if (this.right != null) {
            //4 向右子树进行递归删除
            this.right.deleteNode(no);
        }

    }

    /**
     * 二叉树前序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就放回该node，如果没有找到就放回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("my name is GodFury, the first elden lord.");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("my name is GodFury, the first elden lord.");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("my name is GodFury, the first elden lord.");
        if (this.no == no) {
            return this;
        }
        return null;
    }

    public void preOrder() {
        //前序遍历,先输出父节点
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        //中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}
