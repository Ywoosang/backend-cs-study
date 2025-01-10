package com.ywoosang.exception;

public class ExceptionSample {
    public static void main(String[] args) {
        ExceptionSample sample = new ExceptionSample();
        sample.arrayOutOfBounds();
        sample.checkVariable2();
        sample.multiNoCatch();
    }

    public void arrayOutOfBounds() {
        int[] intArray = new int[5];
        // 배열 범위 밖의 값을 읽으려고 할 때
        // javac 로 컴파일하면 정상적으로 컴파일된다.
        // - 컴파일할 때 점검해주지 않는다.
        // gradle 로 빌드할 때는 빌드가 실패한다.
        // System.out.println(intArray[5]);
    }

    public void checkVariable2() {
        int[] intArray = null;
        try {
            intArray = new int[5];
//            System.out.println(intArray[5]);
            throw new IndexOutOfBoundsException();
        } catch (Exception e) {
            System.out.println(intArray.length);
        }
        System.out.println("This code must run");
    }

    public void multiNoCatch() {
        int[] intArray = new int[5];
        try {
            intArray = null;
            System.out.println("intArray[5] = " + intArray[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException");
        }
    }
}
