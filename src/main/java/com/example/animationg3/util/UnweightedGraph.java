package com.example.animationg3.util;

import com.example.animationg3.model.Block;

import java.util.*;
import java.util.function.Consumer;

public class UnweightedGraph<T> implements Iterable <T>{
    public boolean[][] adjacencyMatrix;
    public Map<Integer, T> nodeLabels;

    public UnweightedGraph(int size) {
        adjacencyMatrix = new boolean[size][size];
        nodeLabels = new HashMap<>();
    }

    // Método para agregar un nodo al grafo
    public void addNode(int nodeId, T label) {
        if (!nodeLabels.containsKey(nodeId)) {
            nodeLabels.put(nodeId, label);
        }
    }

    // Método para eliminar un nodo del grafo
    public void removeNode(int nodeId) {
        if (nodeLabels.containsKey(nodeId)) {
            nodeLabels.remove(nodeId);
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                adjacencyMatrix[nodeId][i] = false;
                adjacencyMatrix[i][nodeId] = false;
            }
        }
    }

    // Método para agregar una arista no ponderada entre dos nodos
    public void addEdge(int source, int destination) {
        if (nodeLabels.containsKey(source) && nodeLabels.containsKey(destination)) {
            adjacencyMatrix[source][destination] = true;
            adjacencyMatrix[destination][source] = true; // For an undirected graph
        }
    }

    // Método para eliminar una arista entre dos nodos
    public void removeEdge(int source, int destination) {
        if (nodeLabels.containsKey(source) && nodeLabels.containsKey(destination)) {
            adjacencyMatrix[source][destination] = false;
            adjacencyMatrix[destination][source] = false; // For an undirected graph
        }
    }

    // Método para buscar un nodo por su etiqueta
    public int findNodeByLabel(T label) {
        for (Map.Entry<Integer, T> entry : nodeLabels.entrySet()) {
            if (entry.getValue().equals(label)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // Método para mostrar el grafo como una cadena de texto
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Graph:\n");

        // Mostrar nodos
        result.append("Nodes: ");
        for (Map.Entry<Integer, T> entry : nodeLabels.entrySet()) {
            result.append(entry.getKey()).append("[").append(entry.getValue()).append("] ");
        }
        result.append("\n");

        // Mostrar aristas
        result.append("Edges:\n");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j]) {
                    result.append("(").append(nodeLabels.get(i)).append(" - ").append(nodeLabels.get(j)).append(")\n");
                }
            }
        }

        return result.toString();
    }

    public void forEachNode(Consumer<Block> action) {
        for (T label : nodeLabels.values()) {
            Block block = (Block) label;
            action.accept(block);
        }
    }

    public List<Integer> findNodesWithBlocksOfType(Class<?> blockType) {
        List<Integer> nodesWithBlocks = new ArrayList<>();

        forEachNode(block -> {
            if (blockType.isInstance(block)) {
                int nodeId = findNodeByLabel((T) block);
                if (nodeId != -1) {
                    nodesWithBlocks.add(nodeId);
                }
            }
        });

        return nodesWithBlocks;
    }


    public static void main(String[] args) {
        // Ejemplo para probar implementación
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);

        graph.addNode(0, "A");
        graph.addNode(1, "B");
        graph.addNode(2, "C");
        graph.addNode(3, "D");

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        System.out.println(graph);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }
}
