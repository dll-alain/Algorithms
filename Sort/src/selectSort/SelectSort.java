package selectSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author dll
 * @date 20220328
 * 选择排序的时间复杂度也是n^2
 * 但是速度较冒泡排序快上不少
 */
public class SelectSort {

    public static void selectSort(int[] arr) {
        //1 找出最小数，放在首位
        //假设首位数是最小的
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                if (min < arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
                //System.out.println("有效排序");
                //System.out.println(Arrays.toString(arr));
            }

        }

    }

    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i< arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("选择排序前时间"+date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("选择排序后时间"+date2Str);
    }


}
