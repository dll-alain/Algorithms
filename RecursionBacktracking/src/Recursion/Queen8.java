package Recursion;

/**
 * @author dll
 * @date 20220216
 */
public class Queen8 {
    int max = 8;
    int[] array = new int[max];
    static int count = 0;

    /**
     * 放置第n个皇后
     * @param n 放置第几个皇后
     */
    private void check(int n) {
        if (n == max) {
            //n = 8 时，八个皇后放好
            print();
            return;
        }
        //依次放入皇后
        for (int i = 0; i < max; i++) {
            //先把当前的皇后放到改行的第一列
            array[n] = i;
            if (judge(n)) {
                //如果不冲突就放下一个
                check(n + 1);
            }
            //如果冲突，就继续执行for，i++,后就将该皇后放到本行下一列
        }

    }


    /**
     * 将每个皇后的位置输出
     */
    private void print() {
        count++;
        for (int j : array) {
            System.out.print(j + "\t");
        }
        System.out.println();
    }

    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 第n个皇后
     * @return t 不冲突, f 冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // y1 -y2 / x1 -x2 相当于斜率，斜率为1相当于在斜线上
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有" + count + "种解法");
    }


}
