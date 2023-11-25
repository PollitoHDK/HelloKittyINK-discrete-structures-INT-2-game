import com.example.animationg3.model.Block;
import com.example.animationg3.util.UnweightedGraph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UnweightedGraphTest {

    @Test
    public void testAddNode() {
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);
        graph.addNode(0, "A");
        assertEquals("A", ((UnweightedGraph<String>) graph).nodeLabels.get(0));
    }

    @Test
    public void testRemoveNode() {
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);
        graph.addNode(0, "A");
        graph.removeNode(0);
        assertNull(graph.nodeLabels.get(0));
    }

    @Test
    public void testAddEdge() {
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);
        graph.addNode(0, "A");
        graph.addNode(1, "B");
        graph.addEdge(0, 1);
        assertTrue(((UnweightedGraph<String>) graph).adjacencyMatrix[0][1]);
        assertTrue(graph.adjacencyMatrix[1][0]);
    }

    @Test
    public void testRemoveEdge() {
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);
        graph.addNode(0, "A");
        graph.addNode(1, "B");
        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);
        assertFalse(graph.adjacencyMatrix[0][1]);
        assertFalse(graph.adjacencyMatrix[1][0]);
    }

    @Test
    public void testFindNodeByLabel() {
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);
        graph.addNode(0, "A");
        int nodeId = graph.findNodeByLabel("A");
        assertEquals(0, nodeId);
    }

    @Test
    public void testFindNodesWithBlocksOfType() {
        UnweightedGraph<String> graph = new UnweightedGraph<>(4);
        graph.addNode(0, "m"); // Agrega un bloque al nodo
        graph.addNode(1, "B");
        graph.addNode(2, "W"); // Agrega otro bloque al nodo

        List<Integer> nodesWithBlocks = graph.findNodesWithBlocksOfType(Block.class);
        assertEquals(2, nodesWithBlocks.size());
        assertTrue(((List<?>) nodesWithBlocks).contains(0));
        assertTrue(nodesWithBlocks.contains(2));
    }

    // Puedes agregar más pruebas según tus necesidades

}
