package insertSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author dll
 * @date 20220329
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        int insertValue;
        int insertIndex;
        //第一轮{34, 101, 119, 1}
        //定义待插入的数
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;
            //放在index越界
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //插入位置已找到，就在insertIndex+1的位置上
//            if (insertIndex + 1 != i) {
//                arr[insertIndex + 1] = insertValue;
//            }
            arr[insertIndex + 1] = insertValue;
            //System.out.println("第"+i+"轮插入");
            //System.out.println(Arrays.toString(arr));
        }

    }

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, 77, -34};
//        insertSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("插入前时间:"+date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("插入后时间:"+date2Str);


    }

}
