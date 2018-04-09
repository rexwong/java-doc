package com.rexwong.base;

/**
 * 位操作
 *
 * @author rexwong
 */
public class BitwiseOperation {

    public static void main(String[] args) {

        int a=129;
        System.out.printf("a转换成二进制: %s \n",Integer.toBinaryString(a));
        int b=128;
        System.out.printf("b转换成二进制: %s \n",Integer.toBinaryString(b));

        /**
         * 1. 与运算符 `&`
         * 两个操作数中位都为1，结果才为1，否则结果为0
         */
        System.out.printf("a 和b 与的结果是：%s ,二进制表示：%s\n",a&b,Integer.toBinaryString(a&b));

        /**
         * 2. 与运算符 `|`
         * 两个位只要有一个为1，那么结果就是1，否则就为0
         */
        System.out.printf("a 和b 或的结果是：%s \n",a|b);

        /**
         * 3. 非运算符 `~`
         * 如果位为0，结果是1，如果位为1，结果是0
         */
        System.out.printf("a 非的结果是：%s,二进制表示：%s\n",~a,Integer.toBinaryString(~a));

        /**
         * 4. 异或运算符 `^`
         * 两个操作数的位中，相同则结果为0，不同则结果为1
         */
        System.out.printf("a 与 b 异或的结果是：%s \n",a^b);
    }
}
