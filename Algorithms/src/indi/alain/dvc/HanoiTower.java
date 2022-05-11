package indi.alain.dvc;

/**
 * 使用分治算法解决
 * @author dll
 * @date 20220506
 */
public class HanoiTower {

    public static void main(String[] args) {
        hanoiTower(30, 'A', 'B', 'C');
    }

    /**
     * 分治算法解决汉诺塔问题
     * @param num 几个盘
     * @param a a 起点
     * @param b b 缓冲区
     * @param c c 目的地
     * a 移动到c， 借助于b
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + " -> " + c);
        } else {
            //如果盘数大于2， 我们总是可以看做是两个盘
            //1 最下面一个盘
            //2 除了最下面一个盘以外的所有盘
            //1 先把最上面的所有盘 从a 移动到b
            hanoiTower(num -1, a, c, b);
            //2 把最下边的盘 a -> c
            System.out.println("第" + num + "个盘从" + a +" -> " + c);
            //3 把b所有的盘移动到C
            hanoiTower(num -1, b, a, c);
        }
    }

}
