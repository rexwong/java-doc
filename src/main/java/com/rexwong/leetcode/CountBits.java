package com.rexwong.leetcode;

/**
 * https://leetcode-cn.com/problems/counting-bits/description/
 */
public class CountBits {

    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 0; i <= num; i++) {
            int binaryStr = bitCount(i);
            res[i]=binaryStr;
        }
        return res;
    }

    /**
     * * * * HAKMEM (Hacks Memo) * * *
     * https://tekpool.wordpress.com/category/bit-count/
     *
     * @param i
     * @return
     */
    private static int bitCount(int i) {
        int uCount;
        uCount = i
                - ((i >> 1) & 033333333333)
                - ((i >> 2) & 011111111111);
        return
                ((uCount + (uCount >> 3))
                        & 030707070707) % 63;
    }

    public static void main(String[] args) {
        int num = 5;
        CountBits bits = new CountBits();
        int[] rs = bits.countBits(num);
    }
}
