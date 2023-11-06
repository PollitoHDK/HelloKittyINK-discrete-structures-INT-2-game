package util;

public class Edge<T> {
    private Vertex<T> start;
    private Vertex<T> end;
    private Integer weight;
    public Edge(Vertex<T> startV ,Vertex<T> endV,Integer inputWeight){

    }

    public Vertex<T> getStart() {
        return start;
    }

    public void setStart(Vertex<T> start) {
        this.start = start;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    public void setEnd(Vertex<T> end) {
        this.end = end;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
