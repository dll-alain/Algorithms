package indi.alain.Match;

import java.util.Arrays;

/**
 * @author d
 */
public class KMPMatch {

    /**
     * 获得部分匹配表
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        //保存部分匹配值
        int[] next = new int[dest.length()];
        //长度为1的字符串部分匹配值必然为0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //回溯是核心
                //循环到dest.charAt(i) == dest.charAt(j）退出
                //while需要放在if前面,不然会把j置为0
                j = next[j -1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                // 部分匹配值加一
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     *
     * @param str1 原字符串
     * @param str2 目标字符串
     * @param next 目标字符串的部分匹配表
     * @return f放回匹配位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //处理str1.charAt ！= str2.charAt

        for (int i = 0, j = 0; i < str1.length(); i++) {
            //处理str1.charAt ！= str2.charAt
            //回溯，是核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j -1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - (j - 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }
}
