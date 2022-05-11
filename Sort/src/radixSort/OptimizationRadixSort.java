package radixSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author dll
 */
public class OptimizationRadixSort {

    public static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        //得到最大的位数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / ((int) (Math.pow(10, i))) % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]++] = arr[j];
            }
            int index = 0;
            for (int l = 0; l < bucketElementCounts.length; l++) {
                if (bucketElementCounts[l] != 0) {
                    for (int k = 0; k < bucketElementCounts[l]; k++) {
                        arr[index++] = bucket[l][k];
                    }
                }
                bucketElementCounts[l] = 0;
            }
            //System.out.println("第" + (i + 1) + "次排序 res = " + Arrays.toString(arr));
        }


    }

    public static void main(String[] args) {

        //int[] arr1 = {53, 77, 0, 44, 33, 88, 111, 9, 211, 985};
        //radixSort(arr1);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("基数排序前时间: "+date1Str);
        radixSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("基数排序后时间: "+date2Str);



    }

}
