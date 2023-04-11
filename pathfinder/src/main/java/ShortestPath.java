/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 21:48
 */
import Graph.*;


import java.util.List;

public abstract class ShortestPath implements PathFinder{

    List<Nodes> nodesList;
    List<Edges> edgeList;
    public ShortestPath(Graph g) {
        nodesList = g.getNodesList();
        edgeList = g.getEdgesList();
    }
}
