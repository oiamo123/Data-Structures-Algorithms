package org.example.graphs;

import java.util.*;

public class Dijkstra {
    class Edge {
        Node vertex;
        int weight;

        public Edge(Node vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    class Node {
        int val;
        Set<Edge> edges;

        public Node(int val) {
            this.val = val;
        }

        public void setEdges(Set<Edge> edges) {
            this.edges = edges;
        }
    }

    public void algorithm(Node node) {
        Map<Node, Integer> distance = new HashMap<Node, Integer>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        for (Edge edge : node.edges) {
            distance.put(edge.vertex, Integer.MAX_VALUE);
        }

        pq.offer(node);
        distance.put(node, 0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentDistance = distance.get(current);

            for (Edge edge : current.edges) {
                int newDistance = currentDistance + edge.weight;

                if (newDistance < distance.getOrDefault(edge.vertex, Integer.MAX_VALUE)) {
                    distance.put(edge.vertex, newDistance);
                    pq.offer(edge.vertex);
                }
            }
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, Integer> visited = new HashMap<>();
        visited.put(beginWord, 1);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = visited.get(current);

            for (int i = 0; i < current.length(); i++) {
                char[] wordArray = current.toCharArray();

                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);

                    if (newWord.equals(endWord)) {
                        return currentDistance + 1;
                    }

                    if (wordSet.contains(newWord) && !visited.containsKey(newWord)) {
                        visited.put(newWord, currentDistance + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }

        return 0;
    }
}
