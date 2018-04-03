package com.rexwong.argithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListRemove {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("beijing");
        list.add("shanghai");
        list.add("shanghai");
        list.add("guangzhou");
        list.add("shenzhen");
        list.add("hangzhou");
        remove32(list, "shanghai");
    }
    /**
     * 正确
     */
    public static void remove32(List<String> list, String target){
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                iter.remove();
            }
        }
        list.forEach(System.out::println);
    }
}
