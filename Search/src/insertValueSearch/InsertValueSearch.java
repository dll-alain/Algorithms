package insertValueSearch;

import java.util.Arrays;

/**
 * @author dll
 * @date 20220406
 */
public class InsertValueSearch {
    /**
     * 要求数组是有序的
     *
     * @param arr       数组
     * @param left      左边索引
     * @param right     右边索引
     * @param findValue 查找值
     * @return 找到返回相应的下标，没找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        System.out.println("0️⃣");
        //边界判断十分关键
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }
        //mid表达式是关键
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findValue > midValue) {
            //向右边递归查找
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左边递归
            return insertValueSearch(arr, left, mid + 1, findValue);
        } else {
            return mid;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        //System.out.println(Arrays.toString(arr));
        int index = insertValueSearch(arr, 0, arr.length -1, 44);
        System.out.println(index);
    }

}
