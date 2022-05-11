package SparseArray;

import java.io.*;
import java.util.ArrayList;

public class SparseArray {
    private File file = new File("/Users/d/Documents/IdeaProjects/DSAndAlgorithms/SparseArray/map.data");

    public SparseArray() {

    }

    public void setArray(int[][] ints) {
        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file)));
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < ints[0].length; j++) {
                    out.write(ints[i][j]);
                }
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getArray(int l, int w) {
        try {
            int arr;
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)));
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < w; j++) {
                   arr =  in.read();
                   System.out.printf("%d\t", arr);
                }
                System.out.println();
            }
            } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //创建原始的二维数组 11*11
        //0:无子， 1：黑子， 2： 白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[7][8] = 1;
        chessArr1[10][10] = 2;
        chessArr1[6][9] = 1;
        //输出
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        /*
        1. 将二维数组转化为稀疏数组
        2. 先遍历二维数组找到非零的个数
        3. 创建对应的稀疏数组
        4. 给稀疏数组赋值
        5. 稀疏数组返回二维数组


         */
        // 2
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }
        // 3,4
        int sparseArr[][] = new int[count+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = count;

        //index 用于记录第几个非零数据
        int index = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //稀疏数组放回二维数组
        //1. 先读取稀疏数组第一行，根据第一行的数据，创建原始二维数组
        // 2. 再读取稀疏数组后几行数据，并赋值给原始的二维数组。

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }


        System.out.println();
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //输入输出文件流
        System.out.println("存储");
        SparseArray sparseArray = new SparseArray();
        sparseArray.setArray(sparseArr);
        sparseArray.getArray(sparseArr.length, sparseArr[0].length);

//         下面方法不太行
//        int length = arrayList.size();
//        int width = arrayList.get(0).split(" ").length;
//        int array[][] = new int[length][width];
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < width; j++) {
//                String s = arrayList.get(i).split(" ")[j];
//                array[i][j] = Integer.parseInt(s);
//            }
//        }

//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.print(array[i][j]+" ");





    }
}
