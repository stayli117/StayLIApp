package com.stayli.shudu;

/**
 * Created by  yahuigao
 * Date: 2019/1/28
 * Time: 10:44
 * Description:
 */
public class ShuduCheck {


    /**
     * 执行所有检验
     *
     * @param resultArray
     * @param row
     * @param col
     * @param value
     * @param checkCross
     * @return
     */
    public static boolean checkAll(final String[][] resultArray, int row, int col, String value, boolean checkCross) {
        // 行校验
        if (!checkRow(resultArray, row, value)) {
            return false;
        }

        // 列校验
        if (!checkColumn(resultArray, col, value)) {
            return false;
        }

        // 宫校验
        if (!checkBlock(resultArray, row, col, value)) {
            return false;
        }

        // 对角线校验
        if (checkCross) {
            // 对角线校验（左上至右下）
            if (!checkLeftTop2RightBottom(resultArray, row, col, value)) {
                return false;
            }
            // 对角线校验（左下至右上）
            if (!checkLeftBottom2RightTop(resultArray, row, col, value)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 行校验
     *
     * @param resultArray
     * @param row
     * @param value
     * @return
     */
    private static boolean checkRow(final String[][] resultArray, int row, String value) {
        int arrayLen = resultArray.length;
        for (int i = 0; i < arrayLen; i++) {
            if (value.equals(resultArray[row][i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 列校验
     *
     * @param resultArray
     * @param col
     * @param value
     * @return
     */
    private static boolean checkColumn(final String[][] resultArray, int col, String value) {
        int arrayLen = resultArray.length;
        for (int i = 0; i < arrayLen; i++) {
            if (value.equals(resultArray[i][col])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 宫校验
     *
     * @param resultArray
     * @param row
     * @param col
     * @param value
     * @return
     */
    private static boolean checkBlock(final String[][] resultArray, int row, int col, String value) {
        int arrayLen = resultArray.length;
        int blockLen = (int) Math.sqrt(arrayLen);
        int blockRowIndex = (int) row / blockLen;
        int blockColIndex = (int) col / blockLen;
        int blockRowStart = blockLen * blockRowIndex;
        int blockColStart = blockLen * blockColIndex;

        for (int i = 0; i < blockLen; i++) {
            int rowIndex = blockRowStart + i;
            for (int j = 0; j < blockLen; j++) {
                int colIndex = blockColStart + j;
                if (value.equals(resultArray[rowIndex][colIndex])) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 对角线校验（左上至右下）
     *
     * @param resultArray
     * @param row
     * @param col
     * @param value
     * @return
     */
    private static boolean checkLeftTop2RightBottom(final String[][] resultArray, int row, int col, String value) {
        if (row == col) {
            int arrayLen = resultArray.length;
            for (int i = 0; i < arrayLen; i++) {
                if (value.equals(resultArray[i][i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 对角线校验（左下至右上）
     *
     * @param resultArray
     * @param row
     * @param col
     * @param value
     * @return
     */
    private static boolean checkLeftBottom2RightTop(final String[][] resultArray, int row, int col, String value) {
        int arrayLen = resultArray.length;
        if ((row + col) == (arrayLen - 1)) {
            for (int i = 0; i < arrayLen; i++) {
                if (value.equals(resultArray[arrayLen - i - 1][i])) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 校验是否已经填好
     *
     * @param value
     * @return
     */
    public static boolean isUnselect(String value) {
        return "".equals(value) || "0".equals(value);
    }
}
