package com.rexwong.argithm;

import com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 强制排序
 *
 */
public class ForeOrder {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("A","B","C","D","E","F","G","H","I");
        Map<String,Integer> forceOrder = ImmutableMap.of("B",1,"D",5,"F",7);
        List<String> result = Lists.newArrayListWithCapacity(list.size());
        for(String element:list){
            if(forceOrder.containsKey(element)){
                result.add(forceOrder.get(element),element);
            }
        }
        int index=0;
        for(String element:list){
            if(forceOrder.containsKey(element)){
                index++;
                continue;
            }
            result.add(index,element);
        }

    }
}
