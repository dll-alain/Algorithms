package indi.alain.dynamicprogramming;


import java.util.Arrays;

/**
 * 动态规划算法解决背包问题
 *
 * @author dll
 * @date 20220506
 */
public class KnapsackProblem {
    /**
     * 1 物品的重量
     * 2 物品的价值
     * 3 背包的容量
     * 4 物品的个数
     */
    private int[] weights;
    private int[] values;
    private int volume;
    private int num;

    public KnapsackProblem(int[] weights, int[] values, int volume) {
        this.weights = weights;
        this.values = values;
        this.volume = volume;
        this.num = values.length;
    }

    public void dynamicPut() {
        //表示前 i 个物品中能够装入容量为j的背包中的价值最大值
        //num + 1 是为了让第一行第一列可以置为0
        int[][] maxValue = new int[this.num + 1][this.volume + 1];
        //为了记录放入商品的情况，需要定义一个二维数组 选择的路径为1，否则为0
        int[][] path = new int[this.num+1][this.volume + 1];

        for (int i = 0; i < maxValue.length; i++) {
            //将第一列置为0
            maxValue[i][0] = 0;
        }
        //第一行置为0
        Arrays.fill(maxValue[0], 0);

        for (int i = 1; i < maxValue.length; i++) {
            for (int j = 1; j < maxValue[0].length; j++) {
                //i and j 都是从1 开始，也就是都是跳过第一行第一列
                if (weights[i - 1] > j) {
                    //程序时从1开始的，因此是i-1
                    maxValue[i][j] = maxValue[i - 1][j];
                } else {
                    //maxValue[i][j] = Math.max(maxValue[i - 1][j], values[i - 1] + maxValue[i - 1][j - weights[i - 1]]);
                    //为了记录path，需要改方法
                    if (maxValue[i -1][j] < values[i-1] + maxValue[i-1][j - weights[i-1]]) {
                        maxValue[i][j] = values[i -1] + maxValue[i-1][j - weights[i -1]];
                        path[i][j] = 1;
                    } else {
                        maxValue[i][j] = maxValue[i-1][j];
                    }
                }
            }
        }

        for (int[] arr : maxValue) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("===================");

        for (int[] arr : path) {
            System.out.println(Arrays.toString(arr));
        }

        //需要逆向遍历path
        int maxRow = path.length -1;
        int maxColumn = path[0].length - 1;
        // 逆向遍历，直到volume使用完毕
        while (maxRow > 0 && maxColumn > 0) {
            if (path[maxRow][maxColumn] == 1) {
                System.out.println("第" + maxRow + "商品放入背包");
                maxColumn -= weights[maxRow - 1];
            }
            maxRow--;
        }
    }

    public static void main(String[] args) {
        int[] weight = {1, 4, 3, 5, 2, 7};
        int[] value = {1500, 8000, 5000, 9000, 4000, 12000};
        int volume = 10;
        KnapsackProblem knapsackProblem = new KnapsackProblem(weight, value, volume);
        knapsackProblem.dynamicPut();

    }
}
