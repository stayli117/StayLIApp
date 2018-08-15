package com.stayli.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Interview {


    public static void main(String[] args) {

        String data = "Android Dev Kit Source";
        String buffer = reverseStringByCharBuffer(data);

        System.out.println("原始：" + data);
        System.out.println("结果：" + buffer);

//        char[] chars = reverseByCharBuffer(data.toCharArray());

//        System.out.println("：" + new String(chars));

//        List<List<Integer>> lists = threeSum();
//        System.out.println("结果 :" + lists + System.currentTimeMillis());
//        System.out.println("结果 :" + lists.size());
//
//
//        setZeroes();

//        groupAnagrams();

//        int re = lengthOfLongestSubstring();
//        System.out.println("长度：" + re);

        longestPalindrome();

        int [] arr = {2,6,4,3,1,7,0,9};
        // 0 6 4 3 1 7 2 9
        // 0 2 4 3 1 7 6 9
        // 0 1 4 3 2 7 6 9
        // 0 1 2 3 4 7 6 9
        //
//        QuickSort.qsort(0,arr.length-1,arr);

        QuickSort.sort(arr,0,arr.length-1);
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba"也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * @return s
     */
    public static String longestPalindrome() {
        String s = "aaa";

        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            System.out.println(len1);
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * <p>
     * 示例：
     * <p>
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * <p>
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * <p>
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     *
     * @return
     */
    public static int lengthOfLongestSubstring() {
        String s = "abbadfdakahfsadbasj";

        int n = s.length(), re = 0;
        int[] index = new int[128]; // 由于字符集的长度有限，此时类似于代替map集合，
        // 保存每个字符所在的索引值
        for (int j = 0, i = 0; j < n; j++) {
            char cj = s.charAt(j); // 获取字符
            int index1 = index[cj]; // 取出字符所在的索引位置
            // index1 当前重复字符的上个索引位置
            // i 上个重复字符的上个索引位置
            if (index1 > i)
                i = index1;//取最大值
            if (re < j - i + 1) {
                re = j - i + 1; // 计算 长度
            }
            index[cj] = j + 1; // 保存索引值
        }
        return re;

    }


    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * <p>
     * 示例:
     * <p>
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * 说明：
     * <p>
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    public static List<List<String>> groupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String temp = String.valueOf(array);
            if (!map.containsKey(temp)) {
                map.put(temp, new ArrayList<String>());
            }
            map.get(temp).add(str);
        }

        return new ArrayList<>(map.values());
    }


    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     * 输入:
     * [
     * [0,1,2,0],
     * [3,4,5,2],
     * [1,3,1,5]
     * ]
     * 输出:
     * [
     * [0,0,0,0],
     * [0,4,5,0],
     * [0,3,1,0]
     * ]
     */
    public static void setZeroes() {
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5},
        };

        boolean isL = false;
        boolean isC = false;

        int l = matrix.length;
        int c = matrix[0].length;

        // 第一行
        for (int i = 0; i < c; i++) {
            if (matrix[0][i] == 0) {
                isL = true;
            }
        }

        // 第一列
        for (int i = 1; i < l; i++) {
            if (matrix[i][0] == 0) {
                isC = true;
            }
        }

        // 开始处理除第一行列之外其他行的数据
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // 填充
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }


        // 恢复第一行 第一列
        if (isL)
            for (int i = 0; i < c; i++) {
                matrix[0][i] = 0;
            }

        if (isC)
            for (int i = 0; i < l; i++) {
                matrix[i][0] = 0;
            }


        for (int[] aMatrix : matrix) {
            System.out.println("----------");
            for (int j = 0; j < c; j++) {
                System.out.print(" " + aMatrix[j]);
            }

        }

    }


    public static String reverseStringByCharBuffer(String str) {

        if (str == null) return null;
        if (str.length() < 1) return str;
        if (!str.contains(" ")) return str;

        char[] temp = str.toCharArray();
        int l = temp.length;
        char[] o = new char[l];
        int preIndex = 0;

        for (int i = 0; i < l + 1; i++) {
            char ch = 0;
            if (i != l) {
                ch = temp[i];
            }
            if (' ' == ch || l == i) {
                int index = l - i;
                if (l != i) {
                    o[index - 1] = ch;
                }

                for (; preIndex < i; preIndex++, index++) {
                    char c = temp[preIndex];
                    o[index] = c;
                }

                preIndex = i + 1;
            }
        }

        return new String(o);
    }

    public static char[] reverseByCharBuffer(char[] data) {
        int l = data.length;
        char[] temp = new char[l];
        for (int i = data.length - 1; i >= 0; i--) {
            temp[l - i - 1] = data[i];
        }
        return temp;
    }


    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     *
     * @return
     */
    public static List<List<Integer>> threeSum() {

        int[] nums = {-5, -5, -4, -4, -4, -2, -2, -2, 0, 0, 0, 1, 1, 3, 4, 4};
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {0, 0, 0};
//        int[] nums = {-4, -1, -1, 0, 1, 2};
        List<List<Integer>> lists = new LinkedList<>();
        Arrays.sort(nums);

        int l = nums.length;
        int truel = l - 2;

        for (int i = 0; i < truel; i++) {
            int mid = i + 1;
            int end = l - 1;
            int a = nums[i];

            while (mid < end) {
                int b = nums[mid];
                int c = nums[end];

                if (a + b + c > 0) {
                    end--;
                    // 从右向左找第一个与之前处理的数不同的数的下标
                    while (mid < end && nums[end] == nums[end + 1]) {
                        end--;
                    }
                } else if (a + b + c < 0) {
                    mid++;
                    // 从左向右找第一个与之前处理的数不同的数的下标
                    while (mid < end && nums[mid] == nums[mid - 1]) {
                        mid++;
                    }
                } else {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(a);
                    list.add(nums[mid]);
                    list.add(nums[end]);
                    lists.add(list);

                    mid++;

                    end--;
                    // 跳过相同的数据
                    while (mid < end && nums[mid] == nums[mid - 1]) {
                        mid++;
                    }

                    while (mid < end && nums[end] == nums[end + 1]) {
                        end--;
                    }
                }

            }

            // 从左向右找第一个与之前处理的数不同的数的下标
            while (i < truel && nums[i + 1] == nums[i]) {
                i++;
            }

        }


        return lists;
    }


}
