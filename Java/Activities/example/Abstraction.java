package com.example;

abstract class Book {
    String title;

    abstract void setTitle(String s);

    String getTitle() {
        return title;
    }
}

class MyBook extends Book {
    public void setTitle(String s) {
        title = s;
    }
}

public class Abstraction {
    public static void main(String[] args) {

        MyBook b = new MyBook();
        b.setTitle("Java Programming");

        System.out.println(b.getTitle());
    }
}