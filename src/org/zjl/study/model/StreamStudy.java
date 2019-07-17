package org.zjl.study.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流练习
 */
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
            System.out.print("大于2逆序："+e+",");
            s3.add(e);
        });
        System.out.println();
        System.out.println("--------------------");
        //使用新list接收
        System.out.println("使用新list接收");
        List<Integer> s4 = s2.stream().map(Test::t2).filter(Test::check).collect(Collectors.toList());
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
        Map<Integer, String> map1 =streamStudyList.stream().collect(Collectors.toMap(StreamStudy::getId, StreamStudy::getValue));
        Map<Integer, StreamStudy> map2 =streamStudyList.stream().collect(Collectors.toMap(StreamStudy::getId, e->e));
        Map<Integer, List<StreamStudy>> map3=streamStudyList1.stream().collect(Collectors.groupingBy(StreamStudy::getId));
        //遍历map1、map2、map3
        System.out.println("遍历map1、map2、map3");
        map1.forEach((key,value)->{System.out.printf("key是%s，value是%s", key, value);System.out.println();});
        map2.forEach((key,value)->{System.out.printf("key是%s，value是%s", key, value);System.out.println();});
        map3.forEach((key,value)->{System.out.printf("key是%s，value是%s", key, value);System.out.println();});
        Optional<Integer> op=s2.stream().findAny();
        System.out.println("op:"+op.get());
    }
}
