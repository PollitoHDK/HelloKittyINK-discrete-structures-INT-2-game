package com.example.animationg3.util;

import java.util.HashMap;
import java.util.Map;

public class Graph<T> {
    private Map<T, Integer> nodeIndexes;
    private T[] nodes;
    private int[][] adjacencyMatrix;

    public Graph(int size) {
        nodeIndexes = new HashMap<>();
        nodes = (T[]) new Object[size];
        adjacencyMatrix = new int[size][size];
    }

    public void addNode(T node, int index) {
        if (!nodeIndexes.containsKey(node)) {
            nodeIndexes.put(node, index);
            nodes[index] = node;
        }
    }

    public void removeNode(T node) {
        Integer index = nodeIndexes.get(node);
        if (index != null) {
            nodeIndexes.remove(node);
            nodes[index] = null;
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                adjacencyMatrix[index][i] = 0;
                adjacencyMatrix[i][index] = 0;
            }
        }
    }

    public void addEdge(T source, T destination, int weight) {
        Integer sourceIndex = nodeIndexes.get(source);
        Integer destinationIndex = nodeIndexes.get(destination);

        if (sourceIndex != null && destinationIndex != null) {
            adjacencyMatrix[sourceIndex][destinationIndex] = weight;
        }
    }

    public void removeEdge(T source, T destination) {
        Integer sourceIndex = nodeIndexes.get(source);
        Integer destinationIndex = nodeIndexes.get(destination);

        if (sourceIndex != null && destinationIndex != null) {
            adjacencyMatrix[sourceIndex][destinationIndex] = 0;
        }
    }

    public void modifyEdge(T source, T destination, int newWeight) {
        Integer sourceIndex = nodeIndexes.get(source);
        Integer destinationIndex = nodeIndexes.get(destination);

        if (sourceIndex != null && destinationIndex != null) {
            adjacencyMatrix[sourceIndex][destinationIndex] = newWeight;
        }
    }

    public int findNodeByLabel(T label) {
        for (Map.Entry<T, Integer> entry : nodeIndexes.entrySet()) {
            if (entry.getKey().equals(label)) {
                return entry.getValue();
            }
        }
        return -1;
    }

    public void floydWarshall() {
        int size = adjacencyMatrix.length;

        // Inicializar la matriz de distancias con las distancias directas entre nodos
        int[][] distanceMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distanceMatrix[i][j] = adjacencyMatrix[i][j];
                if (i != j && distanceMatrix[i][j] == 0) {
                    distanceMatrix[i][j] = Integer.MAX_VALUE; // Infinito para nodos no conectados
                }
            }
        }

        // Calcular distancias mínimas a través de cada nodo intermedio
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distanceMatrix[i][k] != Integer.MAX_VALUE &&
                            distanceMatrix[k][j] != Integer.MAX_VALUE &&
                            distanceMatrix[i][k] + distanceMatrix[k][j] < distanceMatrix[i][j]) {
                        distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                    }
                }
            }
        }

        // Imprimir las distancias mínimas
        System.out.println("Shortest Distances (Floyd-Warshall):");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (distanceMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distanceMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Graph:\n");

        // Mostrar nodos
        result.append("Nodes: ");
        for (Map.Entry<T, Integer> entry : nodeIndexes.entrySet()) {
            result.append(entry.getValue()).append("[").append(entry.getKey()).append("] ");
        }
        result.append("\n");

        // Mostrar aristas
        result.append("Edges:\n");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    result.append("(").append(nodes[i]).append(" - ").append(nodes[j]).append("): ").append(adjacencyMatrix[i][j]).append("\n");
                }
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {
        // Ejemplo de uso con tipo específico (Integer para nodos)
        Graph<Integer> graph = new Graph<>(4);

        graph.addNode(0, 0);
        graph.addNode(1, 1);
        graph.addNode(2, 2);
        graph.addNode(3, 3);

        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 3);

        System.out.println(graph);
    }
}
