import com.example.animationg3.util.UnweightedGraph;
import org.junit.Test;

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


}
