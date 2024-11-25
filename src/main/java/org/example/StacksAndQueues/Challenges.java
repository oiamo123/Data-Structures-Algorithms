package org.example.StacksAndQueues;

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
}
