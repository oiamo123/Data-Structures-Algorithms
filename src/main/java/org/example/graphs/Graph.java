package org.example.graphs;


import java.util.*;

// Represent a graph
public class Graph {
    Map<String, Node> nodes; // City names and it's node

    public Graph() {
        nodes = new HashMap<String, Node>();
    }

    // Adds a node to the graph
    public void addNode(String city) {
        nodes.put(city, new Node(city));
    }

    // Adds an edge to a node
    public void addEdge(String city1, String city2, double time) {
        Node node1 = nodes.get(city1);
        Node node2 = nodes.get(city2);

        node1.addEdge(new Edge(city2, time));
        node2.addEdge(new Edge(city1, time));
    }

    // Displays the graph
    public void displayGraph() {
        for (String city : nodes.keySet()) {
            Node node = nodes.get(city);
            System.out.println("City: " + city);
            for (Edge edge : node.getEdges()) {
                System.out.println(edge.city + "(" + edge.time + " hours)" );
            }
            System.out.println();
        }
    }

    // Find the shortest route between two cities
    public void getShortestRoute(String start, String end) {
        Map<String, Double> times = new HashMap<>(); // Keep track of distances
        Map<String, String> previousCities = new HashMap<>(); // stores the city that leads to the shortest path
        // Compares the passed in nodes time with the time in the map
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(times::get));

        // Add all times starting at an infinite value
        for (String city : nodes.keySet()) {
            times.put(city, Double.POSITIVE_INFINITY);
            previousCities.put(city, null);
        }

        // The starting cities time is 0
        times.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String city = queue.poll();
            Node currentNode = nodes.get(city);

            // Check all neighbours of the current city
            for (Edge edge : currentNode.getEdges()) {
                // add the current time plus the edge
                double newTime = times.get(city) + edge.time;

                // if the new time is less than the old time, this is the shorter path
                if (newTime < times.get(edge.city)) {
                    times.put(edge.city, newTime); // add the new time to the map
                    previousCities.put(edge.city, city);
                    queue.add(edge.city);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String currentCity = end;
        while (!currentCity.equals(start)) {
            path.add(currentCity);
            currentCity = previousCities.get(currentCity);
        }
        Collections.reverse(path);
        System.out.println("Shortest path from " + start + " to " + end + ": " + path);
        System.out.println("Total time: " + times.get(end) + " hours");
    }
}
