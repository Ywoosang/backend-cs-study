package com.ywoosang.string;

public class StringPerformance {
    public static void main(String[] args) {
        long startTime, endTime;

        // String
//        String str = "";
//        startTime = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            str += "a"; // 새로운 객체 계속 생성
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("String Time: " + (endTime - startTime));

        // StringBuilder
        StringBuilder sb = new StringBuilder();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            sb.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder Time: " + (endTime - startTime));

        // StringBuffer
        StringBuffer sbf = new StringBuffer();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            sbf.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer Time: " + (endTime - startTime));
    }
}
