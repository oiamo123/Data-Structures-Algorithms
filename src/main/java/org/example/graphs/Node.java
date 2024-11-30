package org.example.graphs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String city; // Name of city
    List<Edge> edges; // All of the paths out of that city

    public Node(String city) {
        this.city = city;
        edges = new ArrayList<Edge>();
    }

    // getters and setters
    public void addEdge(Edge e) {
        edges.add(e);
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
