package sequenceSearch;

/**
 * @author dll
 * @date 20220401
 */
public class SequenceSearch {

    public static int seqSearch(int[] arr, int value) {
        //逐一比对，发现相同的数值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -33, 99, 77, 911};
        int index = seqSearch(arr, 77);
        if (index == -1) {
            System.out.println("what petty");
        } else {
            System.out.println("找到了，下标为 = " + index);
        }

    }
}
