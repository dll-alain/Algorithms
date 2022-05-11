package shellSort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author dll
 * @date 20220329
 * 希尔排序
 */
public class ShellSort {

    /**
     * 冒泡希尔排序
     * 速度较慢
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp;
        int d = 1;
        //第一轮希尔排序,将10个数据分为5组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的元素，步长为5，比如i = 10，就会有三个元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第"+(d++)+"轮排序");
            //System.out.println(Arrays.toString(arr));
        }

    }

    /**
     * 插入希尔排序
     * 速度极快
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        int d = 1;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j-gap]) {
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //退出while时，找到了temp插入的位置
                    arr[j] = temp;
                }
            }
//            System.out.println("第"+(d++)+"轮排序");
//            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
//        int[] arr = {111, 44, 777, -1, 0, 55, 100, 66, 11, 3};
//        shellSort2(arr);

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("插入前时间:"+date1Str);

        shellSort2(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("插入后时间:"+date2Str);


    }

}
