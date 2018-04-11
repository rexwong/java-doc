package com.rexwong.argithm.simhash;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * http://www.cs.princeton.edu/courses/archive/spring04/cos598B/bib/CharikarEstim.pdf
 *
 * @author rexwong
 * 
 */
public class Simhash {

    private IWordSeg wordSeg;

    public Simhash(IWordSeg wordSeg) {
        this.wordSeg = wordSeg;
    }

    public int hammingDistance(int hash1, int hash2) {
        int i = hash1 ^ hash2;
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    public int hammingDistance(long hash1, long hash2) {
        long i = hash1 ^ hash2;
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int) i & 0x7f;
    }

    public long simhash64(String doc) {
        int bitLen = 64;
        int[] bits = new int[bitLen];
        List<String> tokens = wordSeg.tokens(doc);
        for (String t : tokens) {
//            long v = Hashing.murmur3_128().
//                    hashObject(getBytesFromUTF8String(t), Funnels.byteArrayFunnel()).asLong();
            long v = MurmurHash.hash64(t);
            for (int i = bitLen; i >= 1; --i) {
                //对各word的hashcode的每一位，如果该位为1，则simhash相应位的值加1；否则减1
                if (((v >> (bitLen - i)) & 1) == 1) {
                    ++bits[i - 1];
                } else {
                    --bits[i - 1];
                }
            }
        }
        long hash =  0x0000000000000000;
        long one  =  0x0000000000000001;
        //对最后得到的64位的simhash，如果该位大于1，则设为1；否则设为0
        for (int i = bitLen; i >= 1; --i) {
            if (bits[i - 1] > 0) {
                hash |= one;
            }
            one = one << 1;
        }
        return hash;
    }

    public long simhash32(String doc) {
        int bitLen = 32;
        int[] bits = new int[bitLen];
        List<String> tokens = wordSeg.tokens(doc);
        for (String t : tokens) {
//            int v = Hashing.murmur3_32().
//                    hashObject(getBytesFromUTF8String(t), Funnels.byteArrayFunnel()).asInt();
            int v = MurmurHash.hash32(t);
            for (int i = bitLen; i >= 1; --i) {
                if (((v >> (bitLen - i)) & 1) == 1) {
                    ++bits[i - 1];
                } else {
                    --bits[i - 1];
                }
            }
        }
        int hash = 0x00000000;
        int one = 0x00000001;
        for (int i = bitLen; i >= 1; --i) {
            if (bits[i - 1] > 1) {
                hash |= one;
            }
            one = one << 1;
        }
        return hash;
    }

    private static byte[] getBytesFromUTF8String(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }
}
