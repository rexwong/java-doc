package com.rexwong.argithm.simhash;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SimHash2 {

    private String doc;

    private BigInteger intSimHash;

    private String strSimHash;
    /**
     * simhash code的位数
     */
    private int hashbits = 64;

    public SimHash2(String doc) {
        this.doc = doc;
        this.intSimHash = this.simHash();
    }

    public SimHash2(String doc, int hashbits) {
        this.doc = doc;
        this.hashbits = hashbits;
        this.intSimHash = this.simHash();
    }

    /**
     * 获得tokens的 simhash值 整数形式 和 字符串形式
     *
     * @return
     */
    public BigInteger simHash() {
        // 初始化一个64维的特征向量
        final int[] v = new int[this.hashbits];

        // 利用默认的分词器来对字符串进行分词
//        final StringTokenizer stringTokens = new StringTokenizer(this.doc);
//        System.out.println("Token Count: " + stringTokens.countTokens());
        ChineseWordSeg seg = new ChineseWordSeg();
        List<String> tokens = seg.tokens(this.doc);
        for (String token:tokens) {
            final BigInteger t = this.hash(token);

            // 分别处理每个分词，计算其特征hash，而后针对其某一位是否为1，对向量v进行加减1操作
            // 是否也要保证每个token的hash值的位数是hashbits？
            for (int i = 0; i < this.hashbits; i++) {
                final BigInteger bitmask = new BigInteger("1").shiftLeft(i);
                if (t.and(bitmask).signum() != 0) {
                    v[i] += 1;
                } else {
                    v[i] -= 1;
                }
            }
        }
        // 根据simhash的原理，特征向量中大于0的部分，对应于hash值的那一位是1
        BigInteger fingerprint = new BigInteger("0");
        final StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < this.hashbits; i++) {
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        this.strSimHash = simHashBuffer.toString();
        System.out.println(this.strSimHash + " length "
                + this.strSimHash.length());
        return fingerprint;
    }

    private BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        }
        else {
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);

            // 大素数
            BigInteger m = new BigInteger("1000003");

            BigInteger mask = new BigInteger("2")
                    .pow(this.hashbits)
                    .subtract(new BigInteger("1"));

            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }

    public int hammingDistance(SimHash2 other) {
        BigInteger x = this.intSimHash.xor(other.intSimHash);
        int tot = 0;

        // 统计二进制表示的x中1的个数
        // 这是个经典的算法，n&(n-1)可以每次消去最右边的1（最右边可能是一个域）
        while (x.signum() != 0) {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }

    public int getDistance(String str1, String str2) {
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    public List subByDistance(SimHash2 simHash, int distance) {
        int numEach = this.hashbits / (distance + 1);
        List characters = new ArrayList();

        StringBuffer buffer = new StringBuffer();

        int k = 0;
        for (int i = 0; i < this.intSimHash.bitLength(); i++) {
            boolean sr = simHash.intSimHash.testBit(i);

            if (sr) {
                buffer.append("1");
            } else {
                buffer.append("0");
            }

            if ((i + 1) % numEach == 0) {
                BigInteger eachValue = new BigInteger(buffer.toString(), 2);
                buffer.delete(0, buffer.length());
                characters.add(eachValue);
            }
        }

        return characters;
    }

    public static void main(String[] args) {

        String s = "妇联3马上上映，有谁组团去看？";

        SimHash2 hash1 = new SimHash2(s, 64);

        s = "妇联3开启新宇宙，有谁组团去看？";
        SimHash2 hash2 = new SimHash2(s, 64);

        s = "妇联3上映，有谁组团去看？";
        SimHash2 hash3 = new SimHash2(s, 64);

        System.out.println(hash1.hammingDistance(hash2));

        System.out.println(hash1.hammingDistance(hash3));
    }

}