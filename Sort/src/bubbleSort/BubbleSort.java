package bubbleSort;

import java.util.Arrays;

/**
 * @author dll
 * @date 20220328
 */
public class BubbleSort {


    public static void main(String[] args) {
        //进行数组大小-1次排序
        int[] arr = {3, 9, -1, 10, -2};
        //第一趟排序，将最大的数排在最后
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后数组");
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("时间复杂度为n^2");
        /*
        for (int j = 0; j < arr.length - 1; j++) {
            //如果前面的数大于后面的数，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }

        }
        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的数大于后面的数，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }

        }
        System.out.println("第2趟排序后的数组");
        System.out.println(Arrays.toString(arr));
         */

    }

}
