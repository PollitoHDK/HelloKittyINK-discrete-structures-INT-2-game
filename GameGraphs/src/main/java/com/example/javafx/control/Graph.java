package util;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph<T> {
   private ArrayList<Vertex> vertices;
   private boolean isWeighted;
   private boolean isDirected;
   public Graph(boolean isWeighted,boolean isDirected) {
       this.vertices = new ArrayList<>();
       this.isWeighted = isWeighted;
       this.isDirected = isDirected;
   }
   public Vertex<T> addVertex(String data){
       Vertex vertex=new Vertex<>(data);
       this.vertices.add(vertex);
       return vertex;
   }
   public void addEdge(Vertex startVertex,Vertex endVertex,Integer weight){
       if(!this.isWeighted){
           weight=null;
       }
       startVertex.addEdge(endVertex,weight);
       if(!this.isDirected){
           endVertex.addEdge(startVertex,weight);
       }

   }
   public void removeEdge(Vertex startVertex, Vertex endVertex){
       startVertex.removeEdge(endVertex);
       if(!this.isDirected){
           endVertex.removeEdge(startVertex);
       }

   }
   public void removeVertices(Vertex vertex){
       this.vertices.remove(vertex);
   }

}
