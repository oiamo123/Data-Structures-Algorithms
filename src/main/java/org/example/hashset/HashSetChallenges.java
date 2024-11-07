package org.example.hashset;

import java.util.HashSet;
import java.util.Set;

public class HashSetChallenges {
    // Find if a duplicate exists in a given array
    public boolean containsDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    // Find the single number in an array that doesn't contain duplicates ie [2, 2, 1]
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) { // if the set contains the number, remove the number
                set.remove(num);
                continue;
            }

            set.add(num); // add the number to the set
        }

        return set.iterator().next(); // return the first item
    }

    // This solution was on leetcode, but uses XOR (Exclusive or)
    // which I've never head of up until this point
    // but you add the two binary numbers together ie 5(101) and 2(010) which
    // leaves you with 111 which is equal to 6.
    public int xorSingleNumber(int[] nums) {
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        return x;
    }

    // given 2 arrays [1, 2, 3, 4, 5], [1, 2], return the numbers that are present in both
    // Output: [1, 2]
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.contains(num)) {
                intersection.add(num);
            }
        }

        return intersection.stream().mapToInt(i -> i).toArray();
    }
}
