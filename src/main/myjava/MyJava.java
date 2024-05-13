package myjava;

import kotlinBasics.MyCustomKotlinFileName;

public class MyJava {
    public static void main(String[] args) {
        int sum = MyCustomKotlinFileName.add(3, 4);
        System.out.println(sum);
    }
    public int area(int length, int width) {
        return length * width;
    }
}
