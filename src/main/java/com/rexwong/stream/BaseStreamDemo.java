package com.rexwong.stream;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseStreamDemo {
    public static void main(String[] args) {
//        base();
//        countThenSortBycount();
//        updataProAndSortPro();
//        flatMapDemo();
        getAvgProInMap();
    }
    private static void count1(){
        List<Map<String,String>> data = Lists.newArrayList();
        data.add(ImmutableMap.of("date","2018-01-29","channel_level","S","count","2"));
        data.add(ImmutableMap.of("date","2018-01-29","channel_level","R","count","3"));
        data.add(ImmutableMap.of("date","2018-01-29","channel_level","R","count","3"));

        data.add(ImmutableMap.of("date","2018-01-30","channel_level","S","count","5"));
        data.add(ImmutableMap.of("date","2018-01-30","channel_level","R","count","5"));

        data.add(ImmutableMap.of("date","2018-01-31","channel_level","S","count","2"));
        data.add(ImmutableMap.of("date","2018-01-31","channel_level","R","count","2"));

        data.add(ImmutableMap.of("date","2018-02-01","channel_level","S","count","1"));
        data.add(ImmutableMap.of("date","2018-02-01","channel_level","R","count","1"));

        data.add(ImmutableMap.of("date","2018-02-02","channel_level","S","count","6"));
        data.add(ImmutableMap.of("date","2018-02-02","channel_level","R","count","12"));

        data.add(ImmutableMap.of("date","2018-02-03","channel_level","S","count","3"));
        data.add(ImmutableMap.of("date","2018-02-03","channel_level","R","count","1"));

        data.add(ImmutableMap.of("date","2018-02-04","channel_level","S","count","5"));
        data.add(ImmutableMap.of("date","2018-02-04","channel_level","R","count","4"));

        List<Map<String, String>> sdata = data.stream().
                filter(
                        map -> "S".equals(map.get("channel_level"))
                ).collect(Collectors.toList());

        Map<String, DoubleSummaryStatistics> peropleByAge = data.stream().
                collect(
                        Collectors.groupingBy(map -> map.get("date"),
                        Collectors.summarizingDouble(
                                map->Integer.parseInt(map.get("count"))
                        )
                )
                );
        System.out.println(peropleByAge.get(""));
    }
    private static void countThenSortBycount(){
        List<String> list = Arrays.asList("b","a","b","c","a","b",".",".",".");
        Map<String, Long> counted = list.stream().filter(item->!item.equals("."))
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        counted.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);
    }

    private static void base() {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums =  nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        squareNums.forEach(System.out::println);

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        //filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        Arrays.stream(evens).forEach(System.out::println);
        mapCollection();
    }

    private static void mapCollection() {
        //List->Map
        List<Persion> persions =
                Stream.of(
                        new Persion("rex",11),
                        new Persion("rex",21),
                        new Persion("dapeng",2),
                        new Persion("dapeng",2)
                ).collect(Collectors.toList());

        Map<String, Integer> result =
                persions.stream().collect(Collectors.toMap(Persion::getName,Persion::getAge,(persion1, persion2) -> {
                    System.out.println("duplicate key found!");
                    return persion1;
                }));

        System.out.println(result.size());

        List<Persion> persionsresult =
                persions.stream().
                        filter(persion -> persion.getName().equals("rex")).
                        filter(persion -> persion.getAge()<18).
                        collect(Collectors.toList());
        persionsresult.stream().forEach(System.out::println);
    }

    private static void flatMapDemo() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        //flatMap 把 input Stream 中的层级结构扁平化
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
    }
    private static void updataProAndSortPro(){
        Map<Integer,Persion> mapdata = Maps.newHashMap();
        mapdata.put(1,new Persion("rex",11));
        mapdata.put(2,new Persion("rex2",9));
        mapdata.put(3,new Persion("rex3",12));
        mapdata.put(4,new Persion("rex3",5));

        mapdata.forEach((k,v)->{
            int new_age = v.getAge()*10;
            v.setAge(new_age);
        });
        List<Persion> sorted = mapdata.values().stream()
                .sorted(Comparator.comparing(Persion::getAge))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }
    private static void getAvgProInMap() {
        Map<Integer,Persion> mapdata = Maps.newHashMap();
        mapdata.put(1,new Persion("rex",11));
        mapdata.put(2,new Persion("rex2",9));
        double avg = mapdata.values().stream().mapToDouble(Persion::getAge).average().getAsDouble();
        System.out.println(avg);
    }
}
