package binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dll
 * @date 20220401
 */
public class BinarySearch {

    /**
     * 使用二分查找必须要是有序的数组
     *
     * @param arr       数组
     * @param left      左边索引
     * @param right     右边索引
     * @param findValue 要查找分值
     * @return 如果找到返回下标，如果没有找到返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (left > right) {
            //递归完毕,且找不到数值
            return -1;
        }

        if (findValue > midValue) {
            //向右递归
            return binarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左递归
            return binarySearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    public static List<Integer> optimizationBinarySearch(int[] arr, int left, int right, int findValue) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        List<Integer> resultIndexList = new ArrayList<Integer>();

        if (left > right) {
            return new ArrayList<Integer>();
        }
        if (findValue > midValue) {
            return optimizationBinarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return optimizationBinarySearch(arr, left, mid - 1, findValue);
        } else {
            // 在找到mid索引不要马上放回
            //向mid索引左右扫描，将满足目标数值的元素下标加入到合集ArrayList
            //返回ArrayList
            int temp = mid - 1;
            while (true) {
                //向左扫描
                if (temp < 0 || arr[temp] != findValue) {
                    //退出
                    break;
                }
                resultIndexList.add(temp--);
            }
            resultIndexList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                resultIndexList.add(temp++);
            }
            return resultIndexList;
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 77, 77, 77, 77, 77, 77, 100, 911, 10000};
        //int resIndex = binarySearch(arr, 0, arr.length - 1, 77);
        List<Integer> resIndexList = optimizationBinarySearch(arr, 0, arr.length - 1, 77);
        System.out.println("resIndexList = " + resIndexList);
    }

}
