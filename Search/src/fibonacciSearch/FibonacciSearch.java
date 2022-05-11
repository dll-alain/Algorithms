package fibonacciSearch;

import java.util.Arrays;

/**
 * @author dll
 * @date 20220406
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static int[] fibonacci() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 斐波那契查找算法
     * @param arr 数组
     * @param key 查找的关键码
     * @return 返回对应的下标，如果没有放回-1
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        //表示斐波那契分割数值下标
        int k = 0;
        int mid = 0;
        int[] fibonacci = fibonacci();
        //获取斐波那契数值的下标
        while (high > fibonacci[k] - 1) {
            k++;
        }
        //因为fibonacci[k]值可能大于a的长度(斐波那契数列后面数据跨度较大)，因此我们需要使用Arrays类，构造一个新的数组，并指向arr[]
        //copy arr数组, 长度不足的部分使用0填充,举例 temp = {1, 8, 10, 89, 1000, 1234, 0, 0}
        int[] temp = Arrays.copyOf(arr, fibonacci[k]);
        //实际需要使用arr数组的最后数填充, 举例 temp = {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        while (low <= high) {
            mid = low + fibonacci[k - 1] - 1;
            if (key < temp[mid]) {
                //向数组的左边查找
                high = mid - 1;
                //1 全部元素 = 前面的元素 + 后面的元素
                // 2 f[k] = f[k-1]+f[k-2]
                //因为 左边有f[k-1]个元素，所以可以继续拆分出f[k-2], 也就是k--
                k--;
            } else if (key > temp[mid]) {
                //向数组的右边查找
                low = mid + 1;
                k -= 2;
                //1 全部元素= 前面元素 +　后面元素
                //2 f[k] = f[k-1] + f[k-2]
                //3 因为右边有f[k-2], 可以继续拆分出f[k-4], 也就是k -= 2
            } else {
                //找到，需要确定放回什么下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
             }
        }
        return -1;



    }


    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 89);
        System.out.println(index);

    }
}
