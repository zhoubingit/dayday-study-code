package com.study.arithmetic;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {12, 31, 2, 4, 4, 5, 31, 2, 12};
//        System.out.println(singleNumber(nums));
        int num = 0;
        int a = 13;
//        num ^= a;
        num ^= 12;
        System.out.println(num);
    }


    public static int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}