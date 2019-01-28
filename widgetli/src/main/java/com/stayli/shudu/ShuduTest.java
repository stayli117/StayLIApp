package com.stayli.shudu;

/**
 * Created by  yahuigao
 * Date: 2019/1/28
 * Time: 10:56
 * Description:
 */
public class ShuduTest {

    public static void main(String[] args) {

        // 求解给定数独所有可能 

        sudoSelect(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"
        }, new String[][]{
                {"9", "1", "2", "0", "0", "7", "0", "5", "0"},
                {"0", "0", "3", "0", "5", "9", "0", "2", "1"},
                {"0", "0", "5", "4", "1", "2", "0", "0", "9"},
                {"0", "8", "0", "0", "4", "5", "9", "0", "2"},
                {"0", "0", "0", "0", "7", "0", "5", "0", "0"},
                {"5", "0", "4", "0", "6", "0", "0", "1", "0"},
                {"0", "0", "0", "5", "0", "6", "0", "0", "0"},
                {"2", "5", "0", "7", "0", "0", "8", "0", "0"},
                {"0", "3", "0", "0", "0", "0", "0", "9", "5"}
        });
        sudoSelect("002300609000000075100060000504100008060050040800007102000030001250000000907004200");
        sudoSelect("010000000000294000008300709180002040050000080030800096401003800000471000000000020");
        sudoSelect("100200905000080000400600023010005060000060000050400030840001007000070000507002001");
        sudoSelect("300500090400000500002310000053080010000090000060050370000021800001000004080007006");
        sudoSelect("010500000090073000804020000400000100780060029002000005000030207000480060000006090");
    }

    /**
     * 9*9数独给定已选字符串求解
     *
     * @param resultString 数独题目
     */
    public static void sudoSelect(String resultString) {
        String[][] resultArray = MainShuDu.initResultArray(resultString);
        sudoSelect(new String[]{
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        }, resultArray);
    }

    /**
     * N*N数独给定结果数组求解
     *
     * @param dataArray   待选列表
     * @param resultArray 已选结果数组
     */
    public static void sudoSelect(String[] dataArray, final String[][] resultArray) {
        sudoSelect(dataArray, resultArray, false);
    }

    /**
     * 排列选择（从列表中选择n个排列）
     *
     * @param dataArray   待选列表
     * @param resultArray 已选结果
     * @param checkCross  是否校验对角线
     */
    public static void sudoSelect(String[] dataArray, final String[][] resultArray,
                                  boolean checkCross) {
        MainShuDu.sudoSelect(dataArray, resultArray, 0, checkCross);
    }
}
