package org.example.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapChallenges {
    // Determine if the sum of two numbers given in an array add up to a target value
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) { // add numbers to map
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }

    // given two strings, (egg, add), check if the string is isomorphic meaning
    // e maps to a and g maps to d, in the example of egg and add, it's true
    // however if you had eat and add:
    // e = a
    // a = d
    // t != d because a = d
    //
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String sChar = s.substring(i, i + 1);
            String tChar = t.substring(i, i + 1);

            if (!map.containsKey(sChar)) {
                if (map.containsValue(tChar)) {
                    return false;
                }
                map.put(sChar, tChar);
            } else {
                if (!map.get(sChar).equals(tChar)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Given 2 lists, find the matching string with the lowest index
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        int lowestIndex = Integer.MAX_VALUE;

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int indexSum = map.get(list2[i]) + i;

                if (indexSum < lowestIndex) {
                    lowestIndex = indexSum;
                    set.clear();
                    set.add(list2[i]);
                } else if (indexSum == lowestIndex) {
                    set.add(list2[i]);
                }
            }
        }

        return set.toArray(new String[0]);
    }

    // get the first unique character in a given string
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char character : s.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) { // loop over the string
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1; // else return -1
    }

    
}
