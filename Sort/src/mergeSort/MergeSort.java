package mergeSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author dll
 * @date 20220329
 */
public class MergeSort {

    public static void divide(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            divide(arr, left, mid, temp);
            //向右递归分
            divide(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }


    /**
     * 归并排序
     * 分而治之
     * @param arr   排序的原始数组
     * @param left  左边序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //System.out.println("怎么辉石捏？");
        int i = left;
        //初始化i，左边有序序列的初始索引
        int j = mid + 1;
        //右边有序序列的初始索引
        int t = 0;
        //指向temp数组的当前索引

        //1 把左右两边的有序数据填充到temp数组，直到有一边处理完毕
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                //左边有序序列当前元素小于等于右边有序序列的当前元素,便把左边当前元素copy到temp中,让后后移i and t
                temp[t++] = arr[i++];
            } else {
                //右边元素小于左边,便将右边copy到temp中
                temp[t++] = arr[j++];
            }
        }
        //2 把剩余的数据依次全部copy进temp
        while (i <= mid) {
            //左边还有剩余元素
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            //右边还有剩余
            temp[t++] = arr[j++];
        }
        //3 将temp的元素填充的原数组
        t = 0;
        int tempLeft = left;
        //System.out.println("templeft = " + tempLeft + " right = " + right);
        while (tempLeft <= right) {
            //第一次合并 templeft = 0, right =1; 第二次合并templeft = 2 right = 3；第三次是templeft = 0, right = 3;...;第七次tl = 0, right = 7
            arr[tempLeft++] = temp[t++];
        }


    }


    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        divide(arr, 0, arr.length - 1, temp);
//        System.out.println("result = " + Arrays.toString(arr));

        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 80000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("分治前时间:"+date1Str);

        divide(arr, 0, arr.length-1, temp);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("分治后时间:"+date2Str);
    }

}
