package org.example.graphs;

public class Edge {
    String city; // City the edge points to
    double time; // Amount of time it takes to get to the city

    public Edge(String city, double time) {
        this.city = city;
        this.time = time;
    }
}
