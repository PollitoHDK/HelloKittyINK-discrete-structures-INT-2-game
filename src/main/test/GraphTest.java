import com.example.animationg3.util.Graph;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {




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



}
