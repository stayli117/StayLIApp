package com.stayli.utils;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法练习
 */
public class Interview {


    public static void main(String[] args) {

        String data = "Android Dev Kit Source";
        String buffer = reverseStringByCharBuffer(data);

        System.out.println("原始：" + data);
        System.out.println("结果：" + buffer);

//        TypeToken<String, Integer> token = new TypeToken<>();
//
//        Integer abc = token.getType("123");


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

        String s = longestPalindrome();
        System.out.println("回文串： " + s);

        int[] arr = {2, 6, 4, 3, 1, 7, 0, 9};
        // 0 6 4 3 1 7 2 9
        // 0 2 4 3 1 7 6 9
        // 0 1 4 3 2 7 6 9
        // 0 1 2 3 4 7 6 9
        //
        // 两种快排 实质一样
//        QuickSort.qsort(0,arr.length-1,arr);

//        QuickSort.sort(arr,0,arr.length-1);
//        for (int i : arr) {
//            System.out.print(i+" ");
//        }


        //        node1.next = new SingleLinkList.Node<>("g");

//        SingleLinkList<String> nodes = new SingleLinkList<>();
//        nodes.add("a");
//        nodes.add("b");
//        nodes.add("c");
//        nodes.add("d");
//        nodes.add("e");
//
//        SingleLinkList.Node<String> node1 = new SingleLinkList.Node<>("f");
//        nodes.add(node1);
//        nodes.insert(2, new SingleLinkList.Node<>("h"));
//        nodes.insert(0, new SingleLinkList.Node<>("i"));
//        nodes.insert(nodes.size(), new SingleLinkList.Node<>("j"));
//        String node = nodes.get(0);
//        System.out.println("获取index = 0 节点：" + node);
//
//        nodes.display("打印全部节点：");
//        nodes.remove(3);
//        nodes.display("打印删除index = 3 之后：");
//        nodes.remove(new SingleLinkList.Node<>("i"));
//        nodes.display("打印删除 i 之后：");
//        nodes.remove(new SingleLinkList.Node<>("h"));
//        nodes.display("打印删除 h 之后：");


//        nodes.insert(2, "k");
//        nodes.insert(4, "w");
//        nodes.insert(5, "h");
//        nodes.display("排序前： ");

//        insertSort();

//        int fib = fib(18);
//        System.out.println(fib);

        // 对应长度的价格
        int[] price = {3, 5, 8, 13, 14, 16, 18, 20, 24, 30};

        int cut = buttom_up_cut(price);
        int cut1 = bottomCut(price, price.length);
        System.out.println(cut);
        System.out.println(cut1);

        int i = cutSz(8);
        System.out.println(i);
    }

    /**
     * 给你一根长度为N的绳子，请把绳子剪成M段（m,n都是整数），每段绳子的
     * 长度记为k[0],k[1],k[2] … k[n]=n. 请问如何剪绳子使得k[0],k[1],k[2]
     * 的乘积最大
     * m 可以为0 即不切割，不切割默认乘1 k[0]=1
     * <p>
     * c[n]= Math.max(k[j]*c[n-j],c[n-1])
     *
     * @param n
     * @return
     */
    public static int cutSz(int n) {

        int c[] = new int[n + 1];
        c[0] = 1;
        for (int i = 1; i <= n; i++) { // 总长度
            int value = 1;
            for (int j = 1; j <= i; j++) { //每次切割的子长度
                value = Math.max(j * c[(i - j)], value);
            }
            c[i] = value;
            System.out.println(value);
        }


        return c[n];
    }


    /**
     * i 表示需求求解的当前长度 1 <= i <=n
     * j  表示上次求解的长度  j = i-1
     * d[n] 表示当前n长度的价格 d(0)=0
     * <p>
     * d(i) = max(d(j)+d(i-j),d[j])
     *
     * @param price
     * @param len
     * @return
     */
    public static int bottomCut(int[] price, int len) {
        int[] r = new int[len + 1];
        r[0] = 0; // 长度=0 为不切割
        for (int i = 1; i <= len; i++) {
            // 子问题求解过程
//            r[i] = cutSub(price, r, i);
            int value = 0;
            for (int j = 0; j < i; j++) {
                value = Math.max(value, price[j] + r[i - j - 1]);
            }
            // 子问题求解过程
            r[i] = value;
        }
        return r[len];
    }

    private static int cutSub(int[] price, int[] r, int i) {
        int temp = 0;
        for (int j = 1; j <= i; j++) {
            temp = Math.max(price[j - 1] + r[i - j], temp);
        }
        return temp;
    }

    /**
     * 动态规划
     * 钢条切割练习
     * 需要保存不同长度下的最优解
     */
    public static int buttom_up_cut(int[] p) {
        int[] r = new int[p.length + 1];
        // 计算切割不同长度时的最优解
        for (int i = 1; i <= p.length; i++) {
            int q = -1;
            //求解长度为i时的最优解
            for (int j = 1; j <= i; j++) {
                q = Math.max(q, p[j - 1] + r[i - j]);
            }
            r[i] = q;
        }
        return r[p.length];
    }

    /**
     * 自底向上
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 1)
            return n;

        int Memo_i_1 = 0; // 计算使用的第一个位置上的数据

        int Memo_i_2 = 1; // 计算使用的第二个位置上的数据

        int Memo_i = 1; // 计算的结果

        for (int i = 2; i <= n; i++) {
            Memo_i = Memo_i_1 + Memo_i_2;
            Memo_i_1 = Memo_i_2;
            Memo_i_2 = Memo_i;
        }
        return Memo_i;
    }


    /**
     * 自顶向下 备忘录法
     * 动态规划求解斐波那契数列
     */
    public static int fibonacciTop(int n) {

        if (n <= 0) return n;

        // 创建 备忘数组 赋初始值 -1
        int[] Memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Memo[i] = -1;
        }

        return fibTop(n, Memo);

    }

    private static int fibTop(int n, int[] Memo) {

        if (Memo[n] != -1)
            return Memo[n];
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。
        if (n <= 2) {
            Memo[n] = 1;
        } else {
            Memo[n] = fibTop(n - 1, Memo) + fibTop(n - 2, Memo);
        }

        return Memo[n];
    }

    /**
     * 插入排序
     */
    public static void insertSort() {
        int[] array = {38, 36, 56, 17, 69, 45, 13, 27};

        int len = array.length;
        int j, temp;        //temp：记录
        for (int i = 1; i < len; i++) {
            if (array[i - 1] > array[i]) {
                temp = array[i];
                for (j = i - 1; j >= 0; j--) {
                    if (array[j] > temp) {
                        array[j + 1] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }

        for (int i : array) {
            System.out.print(i + " ");
        }

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
//        String s = "aaa";
        String s = "dcbabcd";

        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            System.out.println("len Max: " + len + " i " + i);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * @param s     需要扩展的字符串
     * @param left  扩展字符串左索引
     * @param right 扩展字符串右索引
     * @return
     */
    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        System.out.println("L: " + L);
        System.out.println("R: " + R);
        int len = R - L - 1;
        System.out.println("LEN: " + len);
        return len;
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
