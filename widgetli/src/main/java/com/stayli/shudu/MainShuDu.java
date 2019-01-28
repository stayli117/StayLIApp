package com.stayli.shudu;

import java.util.Arrays;

/**
 * Created by  yahuigao
 * Date: 2019/1/28
 * Time: 10:44
 * Description:
 */
public class MainShuDu {

    private String[][] mShudu;

    public MainShuDu(int n, int m) {
        mShudu = new String[n][m];
    }
// 9*9数独题目

    String[][] resultArray = new String[][]{
            {"0", "2", "0", "0", "0", "0", "0", "0", "0"},
            {"5", "0", "6", "0", "0", "0", "3", "0", "9"},
            {"0", "8", "0", "5", "0", "2", "0", "6", "0"},
            {"0", "0", "5", "0", "7", "0", "1", "0", "0"},
            {"0", "0", "0", "2", "0", "8", "0", "0", "0"},
            {"0", "0", "4", "0", "1", "0", "8", "0", "0"},
            {"0", "5", "0", "8", "0", "7", "0", "3", "0"},
            {"7", "0", "2", "0", "0", "0", "4", "0", "5"},
            {"0", "4", "0", "0", "0", "0", "0", "7", "0"}
    };

    /**
     * 根据待选数组，初始化生成二维结果数组
     * 初始化结果数组
     *
     * @param dataArray 待选列表
     * @return
     */
    public static String[][] initResultArray(String[] dataArray) {
        int arrayLen = dataArray.length;
        String[][] resultArray = new String[arrayLen][arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            for (int j = 0; j < arrayLen; j++) {
                resultArray[i][j] = "0";
            }
        }
        return resultArray;
    }

    /**
     * 根据字符串初始化
     *
     * @param resultString
     * @return
     */
    public static String[][] initResultArray(String resultString) {
        int arrayLen = (int) Math.sqrt(resultString.length());
        String[][] resultArray = new String[arrayLen][arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            for (int j = 0; j < arrayLen; j++) {
                resultArray[i][j] = "" + resultString.charAt(i * arrayLen + j);
            }
        }
        return resultArray;
    }

    /**
     * 数独解题
     *
     * @param dataArray   待选列表
     * @param resultArray 前面（resultIndex-1）个的填空结果
     * @param resultIndex 选择索引，从0开始
     * @param checkCross  是否是对角线数独
     */
    public static void sudoSelect(String[] dataArray, final String[][] resultArray, int resultIndex, boolean checkCross) {
        int resultLen = resultArray.length;
        if (resultIndex >= (int) Math.pow(resultLen, 2)) {
            // 全部填完时，输出排列结果
            printResult(resultArray);
            return;
        }

        int row = (int) resultIndex / resultLen;
        int col = resultIndex % resultLen;
        if (ShuduCheck.isUnselect(resultArray[row][col])) {
            // 逐个尝试，递归选择下一个
            for (int i = 0; i < dataArray.length; i++) {
                if (ShuduCheck.checkAll(resultArray, row, col, dataArray[i], checkCross)) {
                    // 排列结果不存在该项，才可选择
                    String[][] resultCopy = copyArray(resultArray);

                    resultCopy[row][col] = dataArray[i];
                    sudoSelect(dataArray, resultCopy, resultIndex + 1, checkCross);
                }
            }
        } else {
            // 递归选择下一个
            String[][] resultCopy = copyArray(resultArray);
            sudoSelect(dataArray, resultCopy, resultIndex + 1, checkCross);
        }
    }

    private static void printResult(final String[][] resultArray) {
        System.out.println("\n——————————–");
        int arrayLen = resultArray.length;
        for (int i = 0; i < arrayLen; i++) {
            System.out.println(Arrays.asList(resultArray[i]));
        }
    }

    /**
     * 递归过程的填空结果传递，需要复制二维数组
     *
     * @param array
     * @return
     */
    private static String[][] copyArray(final String[][] array) {
        int rowCount = array.length;
        int colCount = array[0].length;
        String[][] copy = new String[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                copy[i][j] = array[i][j];
            }
        }
        return copy;
    }
}
