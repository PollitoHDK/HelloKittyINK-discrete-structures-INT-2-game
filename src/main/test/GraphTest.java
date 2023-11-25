import com.example.animationg3.util.Graph;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {
    @Test
    public void testAddNode() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        assertEquals(Integer.valueOf(0), ((Graph<Integer>) graph).nodes[0]);
    }

    @Test
    public void testRemoveNode() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        graph.removeNode(0);
        assertNull(graph.nodes[0]);
    }

    @Test
    public void testAddEdge() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        graph.addNode(1, 1);
        graph.addEdge(0, 1, 2);
        assertEquals(2, ((Graph<Integer>) graph).adjacencyMatrix[0][1]);
    }

    @Test
    public void testRemoveEdge() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        graph.addNode(1, 1);
        graph.addEdge(0, 1, 2);
        graph.removeEdge(0, 1);
        assertEquals(0, graph.adjacencyMatrix[0][1]);
    }

    @Test
    public void testModifyEdge() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        graph.addNode(1, 1);
        graph.addEdge(0, 1, 2);
        graph.modifyEdge(0, 1, 5);
        assertEquals(5, graph.adjacencyMatrix[0][1]);
    }

    @Test
    public void testFindNodeByLabel() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        int nodeIndex = graph.findNodeByLabel(0);
        assertEquals(0, nodeIndex);
    }

    @Test
    public void testFloydWarshall() {
        Graph<Integer> graph = new Graph<>(4);
        graph.addNode(0, 0);
        graph.addNode(1, 1);
        graph.addNode(2, 2);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 1);

        graph.floydWarshall();

        // Verifica las distancias mínimas después de aplicar el algoritmo de Warshall
        int[][] expectedDistances = {
                {0, 2, 3},
                {2, 0, 1},
                {3, 1, 0}
        };

        assertArrayEquals(expectedDistances, graph.distanceMatrix);
    }

}
