package com.rexwong.argithm.simhash;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class ChineseWordSeg implements IWordSeg{
    @Override
    public List<String> tokens(String doc) {
        List<Term> terms =  NLPTokenizer.segment(doc);
        return terms.stream().map(term->(term.word)).collect(Collectors.toList());
    }

    @Override
    public List<String> tokens(String doc, Set<String> stopWords) {
        return null;
    }
}
