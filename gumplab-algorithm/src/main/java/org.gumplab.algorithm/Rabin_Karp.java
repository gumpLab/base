package org.gumplab.algorithm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 字符串匹配算法
 * 统计模式串（pattern）在主串（str）中出现的下标
 *
 * 思路；
 * 1.计算模式串的 hash 值， 遍历主串进行匹配
 * 2.遍历主串的时候不需要每个都重复计算： 只需要用主串截取后的子串 减去 子串首个字符的hash 加上 子串下一个拼接的字符的hash
 * 3.校验是否相等： 同时采用 hashCode 比较 和 equals 方法
 * 不能只匹配 hash 值： 因为本次匹配采用了按位相加的hash算法，会产生 hash碰撞， 并不保险。 (按位相加：abcd 和 bcda 得到的hash值一样)
 */

public class Rabin_Karp {

    public static void main(String[] args) {
        System.out.println(rabinKarp("ilove-rabinkarp", "-rabin"));
    }

    public static List rabinKarp(String str, String pattern) {
        List indexs = Lists.newArrayList();
        int m = str.length();
        int n = pattern.length();
        // 计算模式串和主串 hash 值： hash算法采用按位相加
        int patternCode = hash(pattern);
        int strCode     = hash(str.substring(0, n));
        // 根据主串和模式串的差值循环
        for (int i = 0; i < m - n - 1; i++) {
            // 不能只匹配 hash 值： 因为采用了按位相加的hash算法，会产生 hash碰撞， 并不保险。 (按位相加：abcd 和 bcda 得到的hash值一样)
            // 校验是否相等： 同时采用 hashCode 比较 和 equals 方法
            if (patternCode == strCode && compareStr(str, pattern, i)) {
                indexs.add(i);
            }
            // 更新主串 i 到 i + n 的 hash 值
            if (i < m - n) {
                strCode = nextHash(str, strCode, i, n);
            }
        }
        return indexs;
    }

    private static int nextHash(String str, int strCode, int i, int n) {
        strCode -= str.charAt(i) - 'a';
        strCode += str.charAt(i + n)  - 'a';
        return strCode;
    }

    private static boolean compareStr(String str, String pattern, int i) {
        String subStr = str.substring(i, i + pattern.length());
        return subStr.equals(pattern);
    }

    private static int hash(String pattern) {
        int hashCode = 0;
        for (int i = 0; i < pattern.length(); i++) {
            hashCode += pattern.charAt(i) - 'a';
        }
        return hashCode;
    }


}
