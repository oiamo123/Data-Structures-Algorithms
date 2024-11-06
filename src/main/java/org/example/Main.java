package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomHashSet hashSet = new CustomHashSet();

        hashSet.add(1);
        hashSet.add(2);

        System.out.println(hashSet.contains(1));
        System.out.println(hashSet.contains(2));

        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
        System.out.println(hashSet.get(4));
    }
}