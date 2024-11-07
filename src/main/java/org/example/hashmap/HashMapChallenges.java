package org.example.hashmap;

import java.util.*;

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

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums1) { // add the item to the map with it's count
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> nums = new ArrayList<>(); // create a list to store duplicates

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) { // if the map contains the number and it's count > 0
                nums.add(num); // add the number
                map.put(num, map.get(num) - 1); // decrement the count
            }
        }

        int[] arr = new int[nums.size()]; // convert it into an array
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }

        return arr;
    }

    // Group anagrams by sorting a given string, and using that as the key
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // sort the string
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }

            map.get(sorted).add(str);
        }

        return new ArrayList<>(map.values());
    }

    /*
    When given a sudoku board ie:
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Ensure that a number only occurs once in a given column and row

    // Rows
    [5, 3, ., ., 7, ., ., ., .]
    // Columns
    [5, 6, ., 8, 4, 7, ., ., .]

     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> columns = new HashMap<>();
        Map<Integer, Set<Character>> boxes = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    char num = board[i][j];
                    int boxIndex = (i / 3) * 3 + (j / 3);

                    rows.putIfAbsent(i, new HashSet<>());
                    columns.putIfAbsent(j, new HashSet<>());
                    boxes.putIfAbsent(boxIndex, new HashSet<>());

                    if (rows.get(i).contains(num) || columns.get(j).contains(num) || boxes.get(boxIndex).contains(num)) {
                        return false;
                    }

                    rows.get(i).add(num);
                    columns.get(j).add(num);
                    boxes.get(boxIndex).add(num);
                }
            }
        }

        return true;
    }
}
