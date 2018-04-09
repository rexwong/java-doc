package com.rexwong.argithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01);
        filter.put(1);
        filter.put(2);
        filter.put(3);

    }
}
