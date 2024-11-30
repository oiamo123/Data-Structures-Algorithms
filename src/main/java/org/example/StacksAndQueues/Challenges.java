package org.example.StacksAndQueues;

import org.example.trees.TreeNode;

import java.util.*;

public class Challenges {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islandCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') { // Found a new island
                    islandCount++;
                    dfs(grid, i, j); // Sink the entire island
                }
            }
        }

        return islandCount;
    }

    private void dfs(char[][] grid, int row, int col) {
        // Check if row/col is out of bounds or cell is not land
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }

        // Mark the current cell as visited
        grid[row][col] = '0';

        // Explore all four directions
        dfs(grid, row + 1, col); // Down
        dfs(grid, row - 1, col); // Up
        dfs(grid, row, col + 1); // Right
        dfs(grid, row, col - 1); // Left
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        String start = "0000";
        if (deadSet.contains(start)) return -1;
        if (start.equals(target)) return 0;

        queue.add(start);
        visited.add(start);
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) return steps;

                for (String neighbor : getNeighbours(current)) {
                    if (!visited.contains(neighbor) && !deadSet.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public List<String> getNeighbours(String current) {
        List<String> neighbours = new ArrayList<>();
        char[] chars = current.toCharArray();

        for (int i = 0; i < 4; i++) {
            char original = chars[i];

            chars[i] = original == '9' ? '0' : (char) (original + 1);
            neighbours.add(new String(chars));

            chars[i] = original == '0' ? '9' : (char) (original - 1);
            neighbours.add(new String(chars));

            chars[i] = original;
        }

        return neighbours;
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    public int numSquaresBFS(int n) {
        if(isPerfectSquare(n)) return 1;

        List<Integer> squares = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        distance[n] = 1;
        queue.add(n);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentDistance = distance[node];

            for (int square : squares) {
                int neighbor = node - square;
                if (isPerfectSquare(neighbor)) {
                    return currentDistance + 1;
                }

                if (neighbor > 0 && distance[neighbor] == -1) {
                    distance[neighbor] = currentDistance + 1;
                    queue.add(neighbor);
                }
            }
        }

        return -1;
    }

    private boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

    public boolean isValid(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) return false;

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char Char : chars) {
            if (Char == '(' || Char == '{' || Char == '[') {
                stack.push(Char);
            } else {
                if (stack.isEmpty()) return false;

                if (Char == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (Char == '}' && stack.peek() == '{') {
                    stack.pop();
                } else if (Char == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];

        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                result[prev] = i - prev;
            }

            stack.push(i);
        }

        return result;
    }


    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num2 + num1);
                        break;
                    case "-":
                        stack.push(num2 - num1);
                        break;
                    case "*":
                        stack.push(num2 * num1);
                        break;
                    case "/":
                        stack.push(num2 / num1);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        return cloneGraph2(node, map);
    }

    public Node cloneGraph2(Node node, Map<Node, Node> map) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);

        Node newNode = new Node(node.val);
        map.put(node, newNode);

        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph2(neighbor, map));
        }

        return newNode;
    }


    public int findTargetSumWays(int[] nums, int target) {
        return backtrack(nums, target, 0, 0);
    }

    public int backtrack(int[] nums, int target, int index, int currentSum) {
        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }

        int add = backtrack(nums, target, index + 1, currentSum + nums[index]);
        int sub = backtrack(nums, target, index + 1, currentSum - nums[index]);

        return add + sub;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        return inorderTraversal2(root, list);
    }

    public List<Integer> inorderTraversal2(TreeNode root, List<Integer> list) {
        if (root == null) return list;
        if (root.left != null) {
            inorderTraversal2(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            inorderTraversal2(root.right, list);
        }
        return list;
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>((word1, word2) -> {
            int freq1 = map.get(word1);
            int freq2 = map.get(word2);

            if (freq1 == freq2) return word2.compareTo(word1);

            return freq1 - freq2;
        });

        for (String word : map.keySet()) {
            queue.add(word);
            if (queue.size() > k) queue.poll();
        }

        List<String> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }

        Collections.reverse(list);
        return list;
    }
}
