package org.zjl.study.model;

/**
 * 方法类
 */
public class Test {
    public static void tt(Integer s1) {
        System.out.println(s1);
    }

    public static int t2(Integer s2) {
        s2++;
        return s2;
    }

    public static boolean check(Integer i) {
        if (i >= 5) {
            return false;
        }
        return true;
    }
}
