package com.rexwong.stream;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseStreamDemo {
    public static void main(String[] args) {
//        base();
        count();
    }
    private static void count(){
        List<Map<String,String>> data = Lists.newArrayList();
        data.add(ImmutableMap.of("date","2018-01-29","channel_level","S","count","2"));
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

        Map<String, DoubleSummaryStatistics> peropleByAge = data.stream().collect(Collectors.groupingBy(map -> map.get("date"), Collectors.summarizingDouble(map->Integer.parseInt(map.get("count")))));
    }
    private static void base() {
        List<String> output = Stream.of("a","b").
                map(String::toUpperCase).
                collect(Collectors.toList());

        output.forEach(System.out::println);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums =  nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        squareNums.forEach(System.out::println);


        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        //flatMap 把 input Stream 中的层级结构扁平化
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        //filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
        Integer[] evens =
                Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        Arrays.stream(evens).forEach(System.out::println);

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
}
