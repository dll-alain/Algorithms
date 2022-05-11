package indi.alain.Match;

/**
 * @author dll
 * @date 20220506
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "沙砾是个大帅比啊，沙砾是个大帅比 ";
        String str2 = "沙砾是个大帅比 ";
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);

    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {
            //保证匹配不越界
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                //i 走了多少步，放回到仅仅只走一步的位置
                i -= (j - 1);
                j = 0;
            }
        }
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
