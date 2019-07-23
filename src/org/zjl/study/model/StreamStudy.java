package org.zjl.study.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流练习
 */
@SuppressWarnings("Duplicates")
public class StreamStudy {
    private int id;
    private String value;

    public StreamStudy(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StreamStudy{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

    public static void main(String[] args) {
        //流转集合
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4);
        List<Integer> s2 = s1.collect(Collectors.toList());
        List<Integer> s3 = new ArrayList<>();
        //遍历list集合+过滤+排序
        System.out.println("遍历list集合+过滤+排序");
        s2.stream().filter(e -> e > 2).sorted(Comparator.reverseOrder()).forEach(e -> {
            System.out.print("大于2逆序：" + e + ",");
            s3.add(e);
        });
        System.out.println();
        System.out.println("--------------------");
        //使用新list接收
        System.out.println("使用新list接收");
        List<Integer> s4 = s2.stream().map(Test::t2).filter(Test::check).collect(Collectors.toList());
        List<Integer> s41 = s2.stream().map(e -> {
            if (e > 2) {
                e = 1;
            } else if (e <= 1) {
                e = 0;
            }
            return e;
        }).filter(Test::check).collect(Collectors.toList());
        s4.stream().forEach(System.out::println);
        System.out.println("--------------------");
        //三种map接收
        List<StreamStudy> streamStudyList = new ArrayList<>();
        List<StreamStudy> streamStudyList1 = new ArrayList<>();
        StreamStudy str1 = new StreamStudy(1, "第一个");
        StreamStudy str2 = new StreamStudy(2, "第二个");
        StreamStudy str3 = new StreamStudy(1, "第三个");
        streamStudyList.add(str1);
        streamStudyList.add(str2);
        streamStudyList1.add(str1);
        streamStudyList1.add(str2);
        streamStudyList1.add(str3);
        Map<Integer, String> map1 = streamStudyList.stream().collect(Collectors.toMap(StreamStudy::getId, StreamStudy::getValue));
        Map<Integer, StreamStudy> map2 = streamStudyList.stream().collect(Collectors.toMap(StreamStudy::getId, e -> e));
        Map<Integer, List<StreamStudy>> map3 = streamStudyList1.stream().collect(Collectors.groupingBy(StreamStudy::getId));
        //遍历map1、map2、map3
        System.out.println("遍历map1、map2、map3");
        map1.forEach((key, value) -> {
            System.out.printf("key是%s，value是%s", key, value);
            System.out.println();
        });
        map2.forEach((key, value) -> {
            System.out.printf("key是%s，value是%s", key, value);
            System.out.println();
        });
        map3.forEach((key, value) -> {
            System.out.printf("key是%s，value是%s", key, value);
            System.out.println();
        });
        System.out.println("Optional使用");
        Optional<Integer> op = s2.stream().findAny();//搜了一下发现findAny并不是随机地选一个，
        // 如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个。
        System.out.println("op:" + op.get());
        System.out.println("--------------------");
        System.out.println("skip(n)跳过前n个，limit(n)是取前n个");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.filter(e -> e > 1).skip(2).limit(1).forEach(System.out::println);
        List<Integer> llll=stream.filter(e -> e > 1).skip(2).limit(1).collect(Collectors.toList());
       /** List<Person> result = list.stream()
                .distinct()
                .collect(toList());//去重*/
       /**filter为过滤，x代表persons中的一个person；
        persons.stream().filter(x -> "ahmook".equals(x.getName()))表示过滤出persons中名字为ahmook的person；
        .findAny()表示将其中任意一个返回；
        .orElse(null)表示如果一个都没找到返回null*/
    }
}
