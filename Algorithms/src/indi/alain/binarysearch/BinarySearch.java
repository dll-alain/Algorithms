package indi.alain.binarysearch;

/**
 * 二分查找的非递归实现
 *
 * @author dll
 * @date 20220506
 */
public class BinarySearch {

    /**
     * 二分查找非递归实现
     * arr 升序排列
     *
     * @param arr    输入的数组
     * @param target 查找目标
     * @return 放回对应的下标，没有找到放回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //需要向左边查找
                right = mid - 1;
            } else {
                //需要向右边查找
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 77, 100};
        int index = binarySearch(arr, 771);
        System.out.println("index = " + index);

    }
}
