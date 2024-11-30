package org.example.algorithms;

import org.example.graphs.Graph;
import org.example.nodes.ListNode;

import java.util.*;

public class Solutions {
    // Used to detect cycles in linked list
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    // Used to find where the cycle starts in a linked list
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

    // recursive implementation of depth first search
    public void DFS(Graph graph, int v, boolean[] visited) {
//        visited[v] = true;
//
//        for (int u: graph.adjList.get(v)) {
//            if (!visited[u]) {
//                DFS(graph, u, visited);
//            }
//        }
    }

    // DFS recursively
    public void initDFS() {
//        List<Graph.Edge> edges = Arrays.asList(
//                // Notice that node 0 is unconnected
//                new Graph.Edge(1, 2), new Graph.Edge(1, 7), new Graph.Edge(1, 8), new Graph.Edge(2, 3),
//                new Graph.Edge(2, 6), new Graph.Edge(3, 4), new Graph.Edge(3, 5), new Graph.Edge(8, 9),
//                new Graph.Edge(8, 12), new Graph.Edge(9, 10), new Graph.Edge(9, 11)
//        );

        int n = 13;

        //Graph graph = new Graph(edges, n);

        //boolean[] visited = new boolean[n];
        //for (int i = 0; i < n; i++) {
            //if (!visited[i]) {
                //DFS(graph, i, visited);
            //}
        //}
    }

    // iterative implementation of dfs
    public void iterativeDFS(Graph graph, int v, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) {
                continue;
            }

            visited[u] = true;
//            List<Integer> adjList = graph.adjList.get(u);
//            for (int i = adjList.size() - 1; i >= 0; i--) {
//                int j = adjList.get(i);
//                if (!visited[j]) {
//                    stack.push(j);
//                }
//            }
        }
    }

    // init of iterative DFS
    public void iterativeInit() {
//        List<Graph.Edge> edges = Arrays.asList(
//                // Notice that node 0 is unconnected
//                new Graph.Edge(1, 2), new Graph.Edge(1, 7), new Graph.Edge(1, 8), new Graph.Edge(2, 3),
//                new Graph.Edge(2, 6), new Graph.Edge(3, 4), new Graph.Edge(3, 5), new Graph.Edge(8, 9),
//                new Graph.Edge(8, 12), new Graph.Edge(9, 10), new Graph.Edge(9, 11)
//        );
//
//        int n = 13;
//
//        Graph graph = new Graph(edges, n);
//        boolean[] visited = new boolean[n];
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                iterativeDFS(graph, i, visited);
//            }
//        }
    }


    // Graph based BFS iteratively
    public void BFS(Graph graph, int v, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>(); // Create queue
        queue.add(v); // Add root to queue
        visited[v] = true; // Mark as visited

        while (!queue.isEmpty()) { // While queue is not empty
            v = queue.poll(); // Get the first item

//            for (int u: graph.adjList.get(v)) { // Get the node from the list, and loop over the sub list
//                if (!visited[u]) { // If visited does not contain the node
//                    queue.add(u); // Add it to the queue to check it's neighbours
//                    visited[u] = true; // Mark it as visited
//                }
//            }
        }
    }

    // init of iterative BFS
    public void init() {
//        List<Graph.Edge> edges = Arrays.asList( // Create a list of edges
//                new Graph.Edge(1, 2), new Graph.Edge(1, 3), new Graph.Edge(1, 4), new Graph.Edge(2, 5),
//                new Graph.Edge(2, 6), new Graph.Edge(5, 9), new Graph.Edge(5, 10), new Graph.Edge(4, 7),
//                new Graph.Edge(4, 8), new Graph.Edge(7, 11), new Graph.Edge(7, 12)
//        );
//
//        int n = edges.size(); // Get the size
//
//        Graph graph = new Graph(edges, n); // Create a new graph
//
//        boolean[] visited = new boolean[n]; // Initialize a list of visited
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                BFS(graph, i, visited);
//            }
//        }
    }

    // BFS recursively
    public void recursiveBFS(Graph graph, Queue<Integer> q, boolean[] visited) {
        if (q.isEmpty()) {
            return;
        }

        int val = q.poll();

//        for (int u: graph.adjList.get(val)) {
//            if (!visited[u]) {
//                q.add(u);
//                visited[u] = true;
//            }
//        }

        recursiveBFS(graph, q, visited);
    }

    // Recursive BFS init
    public void recursiveInit() {
//        List<Graph.Edge> edges = Arrays.asList( // Create a list of edges
//                new Graph.Edge(1, 2), new Graph.Edge(1, 3), new Graph.Edge(1, 4), new Graph.Edge(2, 5),
//                new Graph.Edge(2, 6), new Graph.Edge(5, 9), new Graph.Edge(5, 10), new Graph.Edge(4, 7),
//                new Graph.Edge(4, 8), new Graph.Edge(7, 11), new Graph.Edge(7, 12)
//        );
//
//        int n = edges.size(); // Get the size
//
//        Graph graph = new Graph(edges, n); // Create a new graph
//
//        boolean[] visited = new boolean[n]; // Initialize a list of visited
//
//        Queue<Integer> q = new ArrayDeque<>();
//
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                visited[i] = true;
//                q.add(i);
//                recursiveBFS(graph, q, visited);
//            }
//        }
    }

    int[] parent;

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY; // Union by setting root of x to root of y
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // loop over all edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (find(u) == find(v)) {
                // If both nodes have the same root, adding this edge forms a cycle
                return edge;
            } else {
                union(u, v); // Otherwise, union the nodes
            }
        }

        // This line should never be reached if there is always a redundant edge
        return new int[0];
    }
}
