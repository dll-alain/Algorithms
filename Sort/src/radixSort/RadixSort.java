package radixSort;

import java.util.Arrays;

/**
 * @author dll
 * @date 20220330
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        //定义10个桶， 每个桶都是一个一维数组,用来存储0-9的数字
        //二维数组包含10个一维数组，每个一维数组大小为arr.length
        int[][] bucket = new int[10][arr.length];
        //为了记录各个桶每次存储数据的个数
        int[] bucketElementCounts = new int[10];

        //第一步，将没个数的个位数放入桶中
        for (int j = 0; j < arr.length; j++) {
            //取出元素个位,放入相应桶中
            int digitOfElement = arr[j] % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放回原来的数组)
        int index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                //k桶中有数据,暨第k个一维数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            //在把桶里的数据放回数组后，需要吧bucketElementCounts计数器清零
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮对res = " + Arrays.toString(arr));

        //第二步，将每个数的十位放入桶中
        for (int j = 0; j < arr.length; j++) {
            //取出元素个位,放入相应桶中
            int digitOfElement = arr[j] / 10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放回原来的数组)
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                //k桶中有数据,暨第k个一维数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            //在把桶里的数据放回数组后，需要吧bucketElementCounts计数器清零
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮对res = " + Arrays.toString(arr));

        //第三步将百位放入桶中
        for (int j = 0; j < arr.length; j++) {
            //取出元素个位,放入相应桶中
            int digitOfElement = arr[j] / 100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放回原来的数组)
        index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) {
                //k桶中有数据,暨第k个一维数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            //在把桶里的数据放回数组后，需要吧bucketElementCounts计数器清零
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮对res = " + Arrays.toString(arr));


    }

    public static void main(String[] args) {
        int[] arr = {53, 77, 0, 44, 33, 88, 111, 9, 211, 985};
        radixSort(arr);

    }
}
