package quickSort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序，时间换空间
 * @author dll
 * @date 20220329
 */
public class QuickSort {

    public static void quickSort(int[] arr, int left, int right) {
        int temp;
        int leftIndex = left;
        int rightIndex = right;
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        //比pivot小的值放左边，
        while (rightIndex > leftIndex) {
            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }
            if (leftIndex >= rightIndex) {
                //当leftindex > rightindex ，说明左右两边已经提纯，左边均小于pivot，右边均大于pivot
                break;
            }
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;

            if (arr[leftIndex] == pivot) {
                rightIndex--;
            }
            if (arr[rightIndex] == pivot) {
                leftIndex++;
            }
        }
        //当两个指针双向奔赴后，需要拆开,这时候leftindex已经到了右半区，rightindex已经到了左半区
        if (leftIndex == rightIndex) {
            rightIndex--;
            leftIndex++;
        }
        //向左递归
        if (left < rightIndex) {
            quickSort(arr, left, rightIndex);
        }
        //向右递归
        if (right > leftIndex) {
            quickSort(arr, leftIndex, right);
        }


    }

    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 77, 11, -33};
        //quickSort(arr, 0, arr.length - 1);
        //System.out.println("res= " + Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("快排前时间:"+date1Str);

        quickSort(arr, 0, arr.length-1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("快排后时间:"+date2Str);


    }
}
