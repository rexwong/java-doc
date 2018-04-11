package com.rexwong.argithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01);
        filter.put(1);
        filter.put(2);
        filter.put(3);
        List<Term> terms =  NLPTokenizer.segment("妇联3开启新宇宙，有谁组团去看？");
        terms.stream().forEach(term -> {
            System.out.println(term.word);
        });
    }
}
