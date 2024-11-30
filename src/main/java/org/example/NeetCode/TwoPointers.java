package org.example.NeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// -4, -1, -1, 0, 1, 2

public class TwoPointers {
    public int trap(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                // Move left pointer
                if (height[left] >= leftMax) {
                    leftMax = height[left];  // Update left max height
                } else {
                    max += leftMax - height[left];  // Calculate trapped water
                }
                left++;
            } else {
                // Move right pointer
                if (height[right] >= rightMax) {
                    rightMax = height[right];  // Update right max height
                } else {
                    max += rightMax - height[right];  // Calculate trapped water
                }
                right--;
            }
        }

        return max;
    }

    public int maxArea(int[] heights) {
        int left = 0, right = heights.length - 1, maxArea = 0;
        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort the array first to avoid duplicate triplets.

        for (int cur = 0; cur < nums.length - 2; cur++) {
            if (cur > 0 && nums[cur] == nums[cur - 1]) {
                continue;
            }

            int start = cur + 1;
            int end = nums.length - 1;

            while (start < end) {
                int sum = nums[cur] + nums[start] + nums[end];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[cur], nums[start], nums[end]));

                    // Move both pointers to skip duplicates
                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }

                    start++;
                    end--;
                } else if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        return result;
    }

    public int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{i + 1, map.get(target - numbers[i]) + 1};
            }
        }

        return null;
    }

    public boolean isPalindrome(String s) {
        // ignore any spaces and characters
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z0-9]", "");

        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
