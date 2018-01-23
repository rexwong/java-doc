package com.rexwong.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import java.util.Map;

public class JoinerTest {
    public static void main(String[] args) {
        Map<String,String> queryMap = Maps.newHashMap();
        queryMap.put("22","1");
        queryMap.put("32","2");
        System.out.println(Joiner.on("&").withKeyValueSeparator("==").join(queryMap));
        //22==1&32==2
    }
}
