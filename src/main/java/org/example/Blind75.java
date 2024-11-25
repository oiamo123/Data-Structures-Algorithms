package org.example;

import org.example.nodes.ListNode;

import java.util.*;

public class Blind75 {
    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{ 0, 0 };
    }

    private int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }

            int profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Calculate prefix products
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefix; // Store the prefix product
            prefix *= nums[i];  // Update prefix
        }

        // Step 2: Calculate suffix products and update result
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix; // Combine with the suffix product
            suffix *= nums[i];   // Update suffix
        }

        return result;
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int num : nums) {
            currentSum = Math.max(num, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public int maxProduct(int[] nums) {
        // edge case
        if (nums == null || nums.length == 0) return 0;

        // initialize variables to store the current max and min products
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int globalMaxProduct = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // if the current number is negative, swap the min and max product
            // multiplying by a negative number flips the sign
            if (nums[i] < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            // update max and min product
            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);

            // update global max
            globalMaxProduct = Math.max(globalMaxProduct, maxProduct);
        }

        return globalMaxProduct;
    }

    public int findMin(int[] nums) {
        int smallest = nums[0];
        int last = nums[0];
        for (int num : nums) {
            if (num < smallest) {
                smallest = num;
            } else if (last > num) {
                return smallest;
            }

            last = num;
        }

        return smallest;
    }

    public int search(int[] nums, int target) {
        int beginning = 0;
        int end = nums.length - 1;

        while (beginning <= end) {
            int mid = (beginning + end) / 2;

            // If target is found at middle
            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[beginning] <= nums[mid]) {
                // Check if target is in the left half
                if (nums[beginning] <= target && target < nums[mid]) {
                    end = mid - 1;  // Search in the left half
                } else {
                    beginning = mid + 1;  // Search in the right half
                }
            }
            // Right half is sorted
            else {
                // Check if target is in the right half
                if (nums[mid] < target && target <= nums[end]) {
                    beginning = mid + 1;  // Search in the right half
                } else {
                    end = mid - 1;  // Search in the left half
                }
            }
        }

        return -1;  // Target not found
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // skips duplicates
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // move the pointers inwards after finding the triplet
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public int maxArea(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>(); // initialize the set
        int maxLength = 0; // keep track of the max length
        int start = 0; // initialize the start

        for (int end = 0; end < s.length(); end++) { // loop over the string
            char c = s.charAt(end); // get the current character

            while (window.contains(c)) { // if the hashmap contains the character, remove it
                window.remove(s.charAt(start));
                start++; // increment start by one
            }

            window.add(c); // add the character to the window

            maxLength = Math.max(maxLength, end - start + 1); // check which is longer
        }

        return maxLength; // return the max length
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = head;
        ListNode temp;

        while (head.next != null) {
            temp = head.next; // set temp to head.next
            head.next = temp.next; // set the head.next to head.next.next
            temp.next = prev;
            prev = temp;
        }

        return prev;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }

            head = head.next;
        }

        if (list1 != null) {
            head.next = list1;
        } else if (list2 != null) {
            head.next = list2;
        }

        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode dummy = new ListNode(0);

        for (ListNode node : lists) {
            if (!map.containsKey(node.val)) {
                while (node.next != null) {
                    map.put(node.val, map.getOrDefault(node.val, 0) + 1);
                }
            }
        }

        Integer count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count = entry.getValue();
            while (count > 0) {
                dummy.next = new ListNode(entry.getKey());
                count--;
            }
        }

        return dummy.next;
    }
}
