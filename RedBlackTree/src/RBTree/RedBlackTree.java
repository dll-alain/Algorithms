package RBTree;


/**
 * @author dll
 * @date 20220430
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    /**
     * p                   pr
     * /      \            /       \
     * pl       pr   ==>  p         rr
     * /     \      /    \
     * rl      rr     pl   rl
     * 左旋，通用方法
     * p-pl 和 pr-rr 不需要调整
     * 1 pr-rr 调整为 p-rl  --  rl 变为p的右子节点， p设置为rl的父节点
     * 2 判断 p 是否有父节点, 改变pr的父节点
     * 有  pr.parent = p.parent
     * if p.parent.left = p {p.parent.left = pr}
     * else {p.parent.right = pr}
     * 没有 将pr置为root节点
     * 3 改变p的父节点, 将p置为pr的左子节点
     * p.parent = pr
     * pr.left = p
     *
     * @param p 传入节点
     */
    private void leftRotate(RBNode p) {
        if (p != null) {
            RBNode pr = p.right;
            //1 pr-rl 调整为 p-rl
            p.right = pr.left;
            if (pr.left != null) {
                pr.left.parent = p;
            }
            //2 判断p是否有父节点
            pr.parent = p.parent;
            if (p.parent == null) {
                //如果不存在父节点，则pr置为root
                root = pr;
            } else if (p.parent.left == p) {
                p.parent.left = pr;
            } else {
                p.parent.right = pr;
            }
            //3 改变p的父节点，将p置为pr的左子节点
            p.parent = pr;
            pr.left = p;
        }
    }

    /**
     * 右旋
     *
     * @param p
     */
    private void rightRotate(RBNode p) {
        if (p != null) {
            RBNode pl = p.left;
            //右旋仅修改第一步
            //1 pr-rl 调整为 p-rl
            p.left = pl.right;
            if (pl.right != null) {
                pl.right.parent = p;
            }
            //2 判断p是否有父节点
            pl.parent = p.parent;
            if (p.parent == null) {
                //如果不存在父节点，则pr置为root
                root = pl;
            } else if (p.parent.left == p) {
                p.parent.left = pl;
            } else {
                p.parent.right = pl;
            }
            //3 改变p的父节点，将p置为pr的左子节点
            p.parent = pl;
            pl.right = p;
        }
    }

    /**
     * 红黑树新增节点
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        RBNode temp = root;
        if (root == null) {
            root = new RBNode(key, value == null ? key : value, null);
            return;
        }
        //1 找到插入位置，新增节点的父节点
        RBNode parent;
        int cmp;
        do {
            parent = temp;
            cmp = key.compareTo((K) temp.key);
            if (cmp < 0) {
                //从左侧查找，key小于root key
                temp = temp.left;
            } else if (cmp > 0) {
                //从右侧查找，key 大于 root key
                temp = temp.right;
            } else {
                temp.setValue(value == null ? key : value);
                return;
            }
        } while (temp != null);
        //2 循环结束，找到要插入节点的父节点, 将新增节点添加到父节点的左右子节点
        RBNode node = new RBNode(key, value == null ? key : value, parent);
        if (cmp < 0) {
            //将新节点添加到父节点的左侧
            parent.left = node;
        } else {
            //添加到右侧
            parent.right = node;
        }
        //3 旋转 变色
        fixAfterPut(node);
    }

    private RBNode parentOff(RBNode node) {
        return node != null ? node.parent : null;
    }

    private RBNode leftOff(RBNode node) {
        return node != null ? node.left : null;
    }

    private RBNode rightOff(RBNode node) {
        return node != null ? node.right : null;
    }

    private boolean colorOff(RBNode node) {
        //空节点置为黑色
        return node == null ? BLACK : node.color;
    }

    private void setColor(RBNode node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    /**
     * 在2-3-4树二节点上添加节点非常容易
     * 在2-3-4树三节点天加节点有六种情况
     * 在2-3-4树四节点添加节点右四种情况，且要修改与三节点类似
     *
     * @param node
     */
    private void fixAfterPut(RBNode<K, Object> node) {
        //插入节点为红色
        node.color = RED;
        //1 二节点不需调整, 三四节点需要调整
        while (node != null && node != root && node.parent.color == RED) {
            if (parentOff(node) == leftOff(parentOff(parentOff(node)))) {
                //2 根据父亲节点为爷爷节点的左右，来划分一半，另一半方法一样
                //3 三节点没有叔叔节点，四节点有，可以再次进行划分, 当父亲节点是爷爷节点的左节点时，叔叔节点就位爷爷节点的右节点
                RBNode uncleNode = rightOff(parentOff(parentOff(node)));
                if (colorOff(uncleNode) == RED) {
                    //4 这里判断叔叔节点是否为空，有叔叔节点为红色, 也就是四节点的四种情况, 这里提前判断了父节点是爷爷节点的左节点，也就是只有两种情况
                    //变色加递归
                    //父亲叔叔变为黑色，爷爷变为红色
                    setColor(parentOff(node), BLACK);
                    setColor(uncleNode, BLACK);
                    setColor(parentOff(parentOff(node)), RED);
                    //5 再把改变好的整体作为一个节点插入上面的部分，进行递归
                    node = parentOff(parentOff(node));
                } else {
                    //6 没有叔叔节点也就是三节点的四种情况
                    if (node == rightOff(parentOff(node))) {
                        //7 插入节点在父亲节点的右边, 对父亲节点进行左旋
                        node = parentOff(node);
                        leftRotate(node);
                    } else {
                        //8 变色 父亲节点变为黑色，爷爷节点变为红色
                        setColor(parentOff(node), BLACK);
                        setColor(parentOff(parentOff(node)), RED);
                        //9 根据爷爷节点进行右旋
                        rightRotate(parentOff(parentOff(node)));
                    }
                }
            } else {
                RBNode uncleNode = leftOff(parentOff(parentOff(node)));
                if (colorOff(uncleNode) == RED) {
                    //这里判断叔叔节点是否为空，有叔叔节点为红色, 也就是四节点的四种情况, 这里提前判断了父节点是爷爷节点的左节点，也就是只有两种情况
                    //变色加递归
                    //父亲叔叔变为黑色，爷爷变为红色
                    setColor(parentOff(node), BLACK);
                    setColor(uncleNode, BLACK);
                    setColor(parentOff(parentOff(node)), RED);
                    //再把改变好的整体作为一个节点插入上面的部分，进行递归
                    node = parentOff(parentOff(node));
                } else {
                    //没有叔叔节点也就是三节点的四种情况
                    if (node == leftOff(parentOff(node))) {
                        //插入节点在父亲节点的左, 对父亲节点进行右旋
                        node = parentOff(node);
                        rightRotate(node);
                    } else {
                        //变色 父亲节点变为黑色，爷爷节点变为红色
                        setColor(parentOff(node), BLACK);
                        setColor(parentOff(parentOff(node)), RED);
                        //根据爷爷节点进行左旋
                        leftRotate(parentOff(parentOff(node)));
                    }
                }
            }
        }
        //根节点一定为黑色
        root.color = BLACK;
    }

    /**
     * 找到node的前驱节点
     *
     * @param node 输入节点
     * @return
     */
    private RBNode predecessor(RBNode node) {
        if (node == null) {
            return null;
        } else if (leftOff(node) != null) {
            //左子节点不为空，向左递归
            RBNode p = leftOff(node);
            while (rightOff(p) != null) {
                p = rightOff(p);
            }
            return p;
        } else {
            //node 节点没有子节点，只能向上找子节点
            RBNode p = node.parent;
            RBNode ch = node;
            while (p != null && ch == leftOff(p)) {
                ch = p;
                p = parentOff(p);
            }
            return p;
        }
    }

    private RBNode successor(RBNode node) {
        if (node == null) {
            return null;
        } else if (rightOff(node) != null) {
            //右子节点不为空，向右递归
            RBNode p = rightOff(node);
            while (leftOff(p) != null) {
                p = leftOff(p);
            }
            return p;
        } else {
            //node 节点没有子节点，只能向上找子节点
            RBNode p = node.parent;
            RBNode ch = node;
            while (p != null && ch == rightOff(p)) {
                ch = p;
                p = parentOff(p);
            }
            return p;
        }
    }

    private RBNode getNode(K key) {
        RBNode temp = this.root;
        while (temp != null) {
            int cmp = key.compareTo((K) temp.key);
            if (cmp < 0) {
                temp = temp.left;
            } else if (cmp > 0) {
                temp = temp.right;
            } else {
                return temp;
            }
        }
        return null;
    }

    /**
     * 1 普通二叉树删除
     * 2 删除后调整
     *
     * @param key
     * @return
     */
    public V remove(K key) {
        RBNode node = getNode(key);
        if (node == null) {
            return null;
        }
        V oldValue = (V) node.value;
        deleteNode(node);
        return oldValue;
    }

    /**
     * 二叉树删除
     * 1 直接删除叶子节点
     * 2 若只有一个子节点，用子节点来替代
     * 3 若有两子节点，找到前驱后继节点，则转化为前面两种情况
     *
     * @param node
     */
    private void deleteNode(RBNode node) {
        //先处理情况三
        if (leftOff(node) != null && rightOff(node) != null) {
            //找到要删除节点的前驱或者后继
            RBNode pNode = successor(node);
            //用后继节点的值，覆盖要删除的值
            node.key = pNode.key;
            node.value = pNode.value;
            //这时候要删除的就变成了后继节点，也就是情况一，二
            node = pNode;
        }
        //是否有替换节点来区别情况一和二, 情况一二也有可能是情况三来的
        RBNode replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //情况二
            //1 用替换节点替代node的位置
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (leftOff(parentOff(node)) == node) {
                parentOff(node).left = replacement;
            } else {
                parentOff(node).right = replacement;
            }
            //2 删除node
            node.left = node.right = node.parent = null;
            if (colorOff(node) == BLACK) {
                //删除黑色需要做调整，删除红色不需要
                fixAfterRemove(replacement);
            }
        } else if (node.parent == null) {
            root = null;
        } else {
            //情况一
            //如果要删除节点是黑色，则先调整再删除，如果是红色，直接删除不需要调整
            if (node.color == BLACK) {
                fixAfterRemove(node);
            }
            if (node.parent != null) {
                if (leftOff(parentOff(node)) == node) {
                    parentOff(node).left = null;
                } else {
                    parentOff(node).right = null;
                }
                node = null;
            }
        }
    }

    /**
     * 删除后的调整操作
     * 2-3-4树删除
     * 1 删除3，4节点自己可以搞定
     * 2 删除2节点，需要兄弟借，父亲节点下来，兄弟节点上去
     * 3 兄弟不借
     *
     * @param node
     */
    private void fixAfterRemove(RBNode node) {
        //情况2，3
        while (node != root && colorOff(node) == BLACK) {
            //删除黑色节点才用调整
            if (node == leftOff(parentOff(node))) {
                //左右往往是对称的，因此先写一边 , 这里删除节点是父亲节点的左节点
                //1 找兄弟节点借
                RBNode broNode = rightOff(parentOff(node));
                //判断是不是真的兄弟节点
                if (colorOff(broNode) == RED) {
                    //不是真正的兄弟节点
                    //变色加旋转, 假兄弟节点变为黑色，父亲节点变成红色，旋转
                    setColor(broNode, BLACK);
                    setColor(parentOff(node), RED);
                    //这里对父亲节点进行左旋
                    leftRotate(parentOff(node));
                    //找到真正的兄弟节点
                    broNode = rightOff(parentOff(node));
                }
                //根据兄弟节点有无子节点，判断是否借
                if (colorOff(leftOff(broNode)) == BLACK && colorOff(rightOff(broNode)) == BLACK) {
                    //3 兄弟节点不借
                    //兄弟节点变为红色
                    setColor(broNode, RED);
                    node = parentOff(node);
                } else {
                    //2 兄弟节点借
                    if (colorOff(rightOff(broNode)) == BLACK) {
                        //2.1 兄弟节点的子节点是左节点
                        //右侧为空，左侧不为空
                        setColor(broNode, RED);
                        setColor(leftOff(broNode), BLACK);
                        rightRotate(broNode);
                        //兄弟节点发生变化，重新调整
                        broNode = rightOff(parentOff(node));
                    }
                    //2.2 兄弟节点的子节点是右节点
                    //父亲节点进行一次变色，左旋
                    setColor(broNode, colorOff(parentOff(node)));
                    setColor(parentOff(node), BLACK);
                    setColor(rightOff(broNode), BLACK);

                    leftRotate(parentOff(node));
                    //结束循环
                    node = root;
                }
            } else {
                //左右往往是对称的，因此先写一边 , 这里删除节点是父亲节点的右节点
                //1 找兄弟节点借
                RBNode broNode = leftOff(parentOff(node));
                //判断是不是真的兄弟节点
                if (colorOff(broNode) == RED) {
                    //不是真正的兄弟节点
                    //变色加旋转, 把兄弟节点变为黑色，父亲节点变成红色，旋转
                    setColor(broNode, BLACK);
                    setColor(parentOff(node), RED);
                    //这里对父亲节点进行右旋
                    rightRotate(parentOff(node));
                    //找到真正的兄弟节点
                    broNode = leftOff(parentOff(node));
                }
                //根据兄弟节点有无子节点，判断是否借
                if (colorOff(leftOff(broNode)) == BLACK && colorOff(rightOff(broNode)) == BLACK) {
                    //3 兄弟节点不借
                    //兄弟节点变为红色
                    setColor(broNode, RED);
                    node = parentOff(node);
                } else {
                    //2 兄弟节点借
                    if (colorOff(leftOff(broNode)) == BLACK) {
                        //2.1 兄弟节点的子节点是左节点
                        //右侧为空，左侧不为空
                        setColor(broNode, RED);
                        setColor(rightOff(broNode), BLACK);
                        leftRotate(broNode);
                        //兄弟节点发生变化，重新调整
                        broNode = leftOff(parentOff(node));
                    }
                    //2.2 兄弟节点的子节点是右节点
                    //父亲节点进行一次变色，左旋
                    setColor(broNode, colorOff(parentOff(node)));
                    setColor(parentOff(node), BLACK);
                    setColor(leftOff(broNode), BLACK);

                    rightRotate(parentOff(node));
                    //结束循环
                    node = root;
                }

            }
        }
        //情况1 替换节点为红色，直接变成黑色即可
        //删除了黑色，替换为红色
        setColor(node, BLACK);

    }

    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode(K key, V value, RBNode parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }
    }

}
