package bubbleSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author dll
 * @date 20220328
 */
public class OptimizationBubbleSort {

    public static void bubbleSort(int[] arr) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println("第" + (i + 1) + "趟排序后的数组");
            //System.out.println(Arrays.toString(arr));

            if (!flag) {
                //没有任何交换次序
                break;
            }
            //flag重置为flase以开始下一次循环
            flag = false;
        }
    }

    public static void main(String[] args) {
        //测试冒泡排序速度(n^2), 给八万个数据
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        //System.out.println(Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1str = simpleDateFormat.format(date1);
        System.out.println("冒泡排序的时间复杂度是n^2");
        System.out.println("排序前的时间是"+date1str);

        bubbleSort(arr);

        Date date2 = new Date();
        String date2str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是"+date2str);

//        int[] arr = {3, 9, -1, 10, 20};
//        int temp;
//        boolean flag = false;
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j+1]) {
//                    flag = true;
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
//
//            if (!flag) {
//                //没有任何交换次序
//                break;
//            }
//            //flag重置为flase以开始下一次循环
//            flag = false;
//        }

    }

}
