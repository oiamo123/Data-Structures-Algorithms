package org.example;

import org.example.hashmap.HashMapChallenges;

public class Main {
    public static void main(String[] args) {
        HashMapChallenges challenges = new HashMapChallenges();

        String[] arr1 = {"happy","sad","good"};
        String[] arr2 = {"sad","happy","good"};
        String[] s = challenges.findRestaurant(arr1, arr2);
    }
}