package com.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class demo {

    public static void main(String[] args) {

        // Create a HashSet
        Set<String> names = new HashSet<>();

        // Add values to it
        names.add("Tom");
        names.add("Mary");
        names.add("Peter");
        names.add("Alice");

        // Create an Iterator
        Iterator<String> iterator = names.iterator();

        // Use it to loop through the HashSet
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);
        }
    }
}