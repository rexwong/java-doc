package com.rexwong.argithm.simhash;

import java.util.List;
import java.util.Set;

/**
 * @author rexwong
 */
public interface IWordSeg {

    List<String> tokens(String doc);

    List<String> tokens(String doc, Set<String> stopWords);
}
