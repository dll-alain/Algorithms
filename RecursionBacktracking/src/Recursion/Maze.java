package Recursion;

/**
 * @author dll
 * @date 20220214
 * 先创建一个二维数组模拟迷宫
 */
public class Maze {

    /**
     * 给小球找路径
     *
     * @param map 表示地图
     * @param i   开始找位置坐标
     * @param j   开始坐标
     * @return 找到了返回TRUE
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                //按照策略走,先假定可以走
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j] != 0, 可能是1,2,3
                return false;
            }
        }

    }


    public static void main(String[] args) {
        /*
         * 1表示墙 把上下置为1 再把左右置为1
         * 设置挡板
         * 2 为通路可以走
         * 3 表示可以走但是走不通
         * 策略 下->右->上->左
         */
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][4] = 1;
        map[3][4] = 1;
        map[4][4] = 1;
        map[5][4] = 1;
        map[6][4] = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        setWay(map, 1, 1);
        System.out.println("寻路后地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

    }

}
