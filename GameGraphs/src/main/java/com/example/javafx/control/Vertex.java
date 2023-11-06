package util;

import java.util.ArrayList;

public class Vertex<T> {
    private  T data;
    private ArrayList<Edge> edges;

    public Vertex(T inputData){
        this.data=inputData;
        this.edges=new ArrayList<Edge>();
    }
    public void addEdge(Vertex<T> endVertex, Integer weight){
        edges.add(new Edge<>(this,endVertex,weight));

    }
    public void removeEdge(Vertex<T> endVertex){
        edges.removeIf(edge -> edge.getEnd().equals(endVertex));

    }
    }
