package org.zjl.study.model;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

/**
 * stream流学习
 */
public class Main {
    private int id;
    private String s1;

    public Main(int id, String s1) {
        this.id = id;
        this.s1 = s1;
    }

    @Override
    public String toString() {
        return "Main{" +
                "id=" + id +
                ", s1='" + s1 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(3);
        List<Integer> l1 = new ArrayList<>();
        list.stream().filter(e -> e != 2).sorted(Comparator.reverseOrder()).forEach(e -> {
            e = e + 1;
            l1.add(e);
        });
        l1.stream().forEach(System.out::println);
        List<Integer> l2 = list.stream().map(e ->e*2)
                .filter(e -> e < 5)
                .collect(Collectors.toList());//list每个元素*2
        System.out.println(l1.get(2));
        l2.forEach(System.out::println);
        l2.forEach(Test::tt);

        int i = (int) list.stream().map(Test::t2).filter(Test::check).count();
        List<Main> l = new ArrayList<>();
        Main main = new Main(1, "x1");
        Main m1 = new Main(2, "x2");
        l.add(main);
        l.add(m1);
        // Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, student -> student));
        Map<Integer, Main> map = l.stream().collect(Collectors.toMap(Main::getId, e -> e));
        Map<Integer, List<Main>> map2 =l.stream().collect(Collectors.groupingBy(Main::getId));
        map.forEach((key, value) -> System.out.printf("key是%s，value是%s", key, value));
        System.out.println();
        map2.forEach((key,value)->{
            System.out.println("key="+key);
            System.out.println("value="+value);
        });
        Optional<Integer> op=list.parallelStream().findAny();
        System.out.println("op:"+op.get());

    }
}