import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * creating a Graph.
 * @author Phat Tran
 */
class ThreeTenGraph implements Graph<GraphNode, GraphEdge>, DirectedGraph<GraphNode, GraphEdge> {
  
    /**
     * max number of vertices that a graph can have.
     */
    private static final int MAX_NUMBER_OF_NODES = 200;

    /**
     * list of vertices.
     */
    private GraphNode[] vertexList = null;
    /**
     * list of edges.
     */
    private GraphEdge[][] matrix = null;

    /**
     * constructor.
     */
    public ThreeTenGraph() {
        vertexList = new GraphNode[MAX_NUMBER_OF_NODES];
        matrix = new GraphEdge[MAX_NUMBER_OF_NODES][MAX_NUMBER_OF_NODES];
    }

    /**
     * Returns a view of all edges in this graph. In general, this.
     * obeys the Collection contract, and therefore makes no guarantees.
     * about the ordering of the edges within the set.
     * 
     * @return a Collection view of all edges in this graph
     */
    public Collection<GraphEdge> getEdges() {
        // O(n^2) where n is the max number of nodes in the graph
        // Hint: this method returns a Collection, look at the imports for
        // what collections you could return here.
        LinkedList<GraphEdge> edges = new LinkedList<GraphEdge>();
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
                if (matrix[startVertex][endVertex] != null) {
                    edges.add(matrix[startVertex][endVertex]);// store edges in the linkedlist
                }
            }
        }
        return edges;
    }

    /**
     * Returns a view of all vertices in this graph. In general, this.
     * obeys the Collection contract, and therefore makes no guarantees.
     * about the ordering of the vertices within the set.
     * @return a Collection view of all vertices in this graph
     */
    public Collection<GraphNode> getVertices() {
        // O(n) where n is the max number of nodes in the graph
        // Hint: this method returns a Collection, look at the imports for
        // what collections you could return here.
        LinkedList<GraphNode> nodes = new LinkedList<GraphNode>();
        for (int i = 0; i < MAX_NUMBER_OF_NODES; i++) {
            if (vertexList[i] != null) {
                nodes.add(vertexList[i]);// store nodes in the linkedlist
            }
        }
        return nodes;
    }

    /**
     * Returns the number of edges in this graph.
     * @return the number of edges in this graph
     */
    public int getEdgeCount() {
        // O(n^2) where n is the max number of nodes in the graph
        GraphEdge.edgeCount = getEdges().size();
        return GraphEdge.edgeCount;
    }

    /**
     * Returns the number of vertices in this graph.
     * @return the number of vertices in this graph
     */
    public int getVertexCount() {
        // O(n) where n is the max number of vertices in the graph
        GraphNode.nodeCount = getVertices().size();
        return GraphNode.nodeCount;
    }

    /**
     * Returns true if this graph's vertex collection contains vertex.
     * Equivalent to getVertices().contains(vertex).
     * @param vertex the vertex whose presence is being queried
     * @return true iff this graph contains a vertex vertex
     */
    public boolean containsVertex(GraphNode vertex) {
        // O(1)
        if (vertex == null) {
            return false;
        }
        if (vertexList[vertex.getId()] == null) {
            return false;
        }
        if (vertexList[vertex.getId()].equals(vertex)) {// if the array contains vertex
            return true;
        }
        return false;
    }

    /**
     * Returns a Collection view of the incoming edges incident to vertex.
     * in this graph.
     * @param vertex the vertex whose incoming edges are to be returned
     * @return a Collection view of the incoming edges incident
     *         to vertex in this graph.
     */
    public Collection<GraphEdge> getInEdges(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return null;
        }
        LinkedList<GraphEdge> incomingEdge = new LinkedList<GraphEdge>();
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            if (matrix[startVertex][vertex.getId()] != null) {
                incomingEdge.add(matrix[startVertex][vertex.getId()]);
            }
        }
        return incomingEdge;
    }

    /**
     * Returns a Collection view of the outgoing edges incident to vertex.
     * in this graph.
     * @param vertex the vertex whose outgoing edges are to be returned
     * @return a Collection view of the outgoing edges incident
     *         to vertex in this graph.
     */
    public Collection<GraphEdge> getOutEdges(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return null;
        }
        LinkedList<GraphEdge> outgoingEdge = new LinkedList<GraphEdge>();
        for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
            if (matrix[vertex.getId()][endVertex] != null) {
                outgoingEdge.add(matrix[vertex.getId()][endVertex]);
            }
        }
        return outgoingEdge;
    }

    /**
     * Returns the number of incoming edges incident to vertex.
     * Equivalent to getInEdges(vertex).size().
     * @param vertex the vertex whose indegree is to be calculated
     * @return the number of incoming edges incident to vertex
     */
    public int inDegree(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return 0;
        }
        return getInEdges(vertex).size();
    }

    /**
     * Returns the number of outgoing edges incident to vertex.
     * Equivalent to getOutEdges(vertex).size().
     * @param vertex the vertex whose outdegree is to be calculated
     * @return the number of outgoing edges incident to vertex
     */
    public int outDegree(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return 0;
        }
        return getOutEdges(vertex).size();
    }

    /**
     * Returns a Collection view of the predecessors of vertex.
     * in this graph. A predecessor of vertex is defined as a vertex v.
     * which is connected to.
     * vertex by an edge e, where e is an outgoing edge of.
     * v and an incoming edge of vertex.
     * @param vertex the vertex whose predecessors are to be returned
     * @return a Collection view of the predecessors of
     *         vertex in this graph.
     */
    public Collection<GraphNode> getPredecessors(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return null;
        }
        LinkedList<GraphNode> predecessor = new LinkedList<GraphNode>();
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            // check for outgoing edges for vertex v, which is predecessor of specified vertex
            if (matrix[startVertex][vertex.getId()] != null) {
                predecessor.add(vertexList[startVertex]);
            }
        }
        return predecessor;
    }

    /**
     * Returns a Collection view of the successors of vertex.
     * in this graph. A successor of vertex is defined as a vertex v.
     * which is connected to.
     * vertex by an edge e, where e is an incoming edge of.
     * v and an outgoing edge of vertex. 
     * @param vertex the vertex whose predecessors are to be returned
     * @return a Collection view of the successors of
     *         vertex in this graph.
     */
    public Collection<GraphNode> getSuccessors(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return null;
        }
        LinkedList<GraphNode> successors = new LinkedList<GraphNode>();
        for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
            // check for incoming edges for vertex v, which is the successor for specified vertex
            if (matrix[vertex.getId()][endVertex] != null) {
                successors.add(vertexList[endVertex]);
            }
        }
        return successors;
    }

    /**
     * Returns true if v1 is a predecessor of v2 in this graph.
     * Equivalent to v1.getPredecessors().contains(v2).
     * @param v1 the first vertex to be queried
     * @param v2 the second vertex to be queried
     * @return true if v1 is a predecessor of v2, and false otherwise
     */
    public boolean isPredecessor(GraphNode v1, GraphNode v2) {
        // O(1)
        // check for outgoing edge of v1, which is the predecessor for v2
        if ((v1 == null) || (v2 == null)) {
            return false;
        }
        if (matrix[v1.getId()][v2.getId()] == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if v1 is a successor of v2 in this graph.
     * Equivalent to v1.getSuccessors().contains(v2).
     * @param v1 the first vertex to be queried
     * @param v2 the second vertex to be queried
     * @return true if v1 is a successor of v2, and false otherwise
     */
    public boolean isSuccessor(GraphNode v1, GraphNode v2) {
        // O(1)
        // check fore incoming edge for v1, which is successor for v2
        if ((v1 == null) || (v2 == null)) {
            return false;
        }
        if (matrix[v2.getId()][v1.getId()] == null) {
            return false;
        }
        return true;
    }

    /**
     * Returns the collection of vertices which are connected to vertex.
     * via any edges in this graph.
     * If vertex is connected to itself with a self-loop, then.
     * it will be included in the collection returned.
     * @param vertex the vertex whose neighbors are to be returned
     * @return the collection of vertices which are connected to vertex,
     *         or null if vertex is not present.
     */
    public Collection<GraphNode> getNeighbors(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        // NOTE: there should be no duplicate nodes in the neighbors.
        LinkedList<GraphNode> nodes = new LinkedList<GraphNode>();
        if (vertex == null) {
            return null;
        }
        // check for outgoing edges and self-loop this time
        for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
            if (matrix[vertex.getId()][endVertex] != null) {
                nodes.add(vertexList[endVertex]);
            }
        }
        return nodes;
    }

    /**
     * Returns the number of vertices that are adjacent to vertex
     * (that is, the number of vertices that are incident to edges in vertex's.
     * incident edge set).
     * Equivalent to getNeighbors(vertex).size().
     * @param vertex the vertex whose neighbor count is to be returned
     * @return the number of neighboring vertices
     */
    public int getNeighborCount(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        // NOTE: Not the same as degree() since there should be no duplicate neighbors.
        return getNeighbors(vertex).size();
    }

    /**
     * If directed_edge is a directed edge in this graph, returns the source.
     * otherwise returns null.
     * The source of a directed edge d is defined to be the vertex for which.
     * d is an outgoing edge.
     * directed_edge is guaranteed to be a directed edge if.
     * its EdgeType is DIRECTED.
     * @param directedEdge edge
     * @return the source of directed_edge if it is a directed edge in this graph,
     *         or null otherwise.
     */
    public GraphNode getSource(GraphEdge directedEdge) {
        // O(n^2) where n is the max number of vertices in the graph
        GraphNode vertex = null;
        if ((directedEdge == null) || (getEdgeType(directedEdge) != EdgeType.DIRECTED)) {
            return null;
        }
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
                if (matrix[startVertex][endVertex] != null) {
                    if (matrix[startVertex][endVertex].equals(directedEdge)) {
                        vertex = vertexList[startVertex];
                    }
                }
            }
        }
        return vertex;
    }

    /**
     * If directed_edge is a directed edge in this graph, returns the destination.
     * otherwise returns null.
     * The destination of a directed edge d is defined to be the vertex.
     * incident to d for which.
     * d is an incoming edge.
     * directed_edge is guaranteed to be a directed edge if.
     * its EdgeType is DIRECTED.
     * @param directedEdge edge
     * @return the destination of directed_edge if it is a directed edge in this
     *         graph, or null otherwise.
     */
    public GraphNode getDest(GraphEdge directedEdge) {
        // O(n^2) where n is the max number of vertices in the graph
        GraphNode vertex = null;
        if ((directedEdge == null) || (getEdgeType(directedEdge) != EdgeType.DIRECTED)) {
            return null;
        }
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
                if (matrix[startVertex][endVertex] != null) {
                    if (matrix[startVertex][endVertex].equals(directedEdge)) {
                        vertex = vertexList[endVertex];
                    }
                }
            }
        }
        return vertex;
    }

    /**
     * Returns an edge that connects v1 to v2.
     * If this edge is not uniquely.
     * defined (that is, if the graph contains more than one edge connecting.
     * v1 to v2), any of these edges.
     * may be returned. findEdgeSet(v1, v2) may be.
     * used to return all such edges.
     * Returns null if either of the following is true. 
     *    v1 is not connected to v2.
     *    either v1 or v2 are not present in this graph.
     * Note: for purposes of this method, v1 is only considered to be.
     * connected to.
     * v2 via a given (directed) edge e if.
     * v1 == e.getSource() && v2 == e.getDest() evaluates to true..
     * (v1 and v2 are connected by an undirected edge u if.
     * u is incident to both v1 and v2.).
     * @param v1 first vertex
     * @param v2 second vertex
     * @return an edge that connects v1 to v2
     *         or null if no such edge exists (or either vertex is not present).
     * @see Hypergraph#findEdgeSet(Object, Object)
     */
    public GraphEdge findEdge(GraphNode v1, GraphNode v2) {
        // O(1)
        // if either v1 or v2 are not present in graph
        if ((vertexList[v1.getId()] == null) || (vertexList[v2.getId()] == null)) {
            return null;
        }
        // if v1 is not connect to v2
        if (matrix[v1.getId()][v2.getId()] == null) {
            return null;
        }
        return matrix[v1.getId()][v2.getId()];
    }

    /**
     * Returns true if vertex and edge.
     * are incident to each other.
     * Equivalent to getIncidentEdges(vertex).contains(edge) and to.
     * getIncidentVertices(edge).contains(vertex).
     * @param vertex vertex
     * @param edge edge
     * @return true if vertex and edge
     *         are incident to each other.
     */
    public boolean isIncident(GraphNode vertex, GraphEdge edge) {
        // O(n) where n is the max number of vertices in the graph
        // find incoming and outcoming edges of the vertex
        for (GraphEdge e : getInEdges(vertex)) {
            if (e.equals(edge)) {
                return true;
            }
        }
        for (GraphEdge e : getOutEdges(vertex)) {
            if (e.equals(edge)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds edge e to this graph such that it connects vertex v1 to v2.
     * If this graph does not contain v1, v2.
     * or both, implementations may choose to either silently add.
     * the vertices to the graph or throw an IllegalArgumentException.
     * If this graph assigns edge types to its edges, the edge type of.
     * e will be the default for this graph.
     * See Hypergraph.addEdge() for a listing of possible reasons.
     * for failure.
     * @param e  the edge to be added
     * @param v1 the first vertex to be connected
     * @param v2 the second vertex to be connected
     * @return true if the add is successful, false otherwise
     * @see Hypergraph#addEdge(Object, Collection)
     * @see #addEdge(Object, Object, Object, EdgeType)
     */
    public boolean addEdge(GraphEdge e, GraphNode v1, GraphNode v2) {
        // O(n^2) where n is the max number of vertices in the graph
        // vertices is null, vertices are not in the graph
        if ((v1 == null) || (v2 == null)) {
            return false;
        }
        if (!getVertices().contains(v1) || !getVertices().contains(v2)) {
            throw new IllegalArgumentException();
        }
        if(v1.equals(v2)){
            return false;
        }

        // edge is null
        if (e == null) {
            return false;
        }
        // edge is not directed edge
        if (getEdgeType(e) != EdgeType.DIRECTED) {
            return false;
        }

        for (GraphEdge edge : getEdges()) {
            // if the edge already in the graph, return false
            if (edge.equals(e)) {
                return false;
            }
        }

        // vertices are already connected by another edge
        if (matrix[v1.getId()][v2.getId()] != null) {
            if (!matrix[v1.getId()][v2.getId()].equals(e)) {
                return false;
            }
        }else {// vertices are not connected
            matrix[v1.getId()][v2.getId()] = e;
        }
        return true;
    }

    /**
     * Adds vertex to this graph.
     * Fails if vertex is null or already in the graph.
     * @param vertex the vertex to add
     * @return true if the add is successful, and false otherwise
     * @throws IllegalArgumentException if vertex is null
     */
    public boolean addVertex(GraphNode vertex) {
        // O(1)
        if (vertex == null) {
            throw new IllegalArgumentException();
        }

        if (vertexList[vertex.getId()] != null) {
            // if vertex is already in the graph
            if (vertexList[vertex.getId()].equals(vertex)) {
                return false;
            } else {// another vertex already at this index
                return false;
            }
        }
        //vertex is not in the list, now add
        vertexList[vertex.getId()] = vertex;
        return true;
    }

    /**
     * Removes edge from this graph.
     * Fails if edge is null, or is otherwise not an element of this graph.
     * @param edge the edge to remove
     * @return true if the removal is successful, false otherwise
     */
    public boolean removeEdge(GraphEdge edge) {
        // O(n^2) where n is the max number of vertices in the graph
        if (edge == null) {
            return false;
        }
        if (!getEdges().contains(edge)) {
            return false;
        }
        // loop matrix array
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
                if (matrix[startVertex][endVertex] != null) {
                    if (matrix[startVertex][endVertex].equals(edge)) {// if found edge
                        matrix[startVertex][endVertex] = null;// remove
                        return true;// then return true
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes vertex from this graph.
     * As a side effect, removes any edges e incident to vertex if the.
     * removal of vertex would cause e to be incident to an illegal.
     * number of vertices. (Thus, for example, incident hyperedges are not removed.
     * but.
     * incident edges--which must be connected to a vertex at both endpoints--are.
     * removed.).
     * Fails under the following circumstances.
     *    vertex is not an element of this graph.
     *    vertex is null.
     * @param vertex the vertex to remove
     * @return true if the removal is successful, false otherwise
     */
    public boolean removeVertex(GraphNode vertex) {
        // O(n) where n is the max number of vertices in the graph
        if (vertex == null) {
            return false;
        }
        if (!getVertices().contains(vertex)) {
            return false;
        }
        // incoming edges
        for (int startVertex = 0; startVertex < MAX_NUMBER_OF_NODES; startVertex++) {
            if (matrix[startVertex][vertex.getId()] != null) {
                matrix[startVertex][vertex.getId()] = null;
            }
        }
        // outgoing edges
        for (int endVertex = 0; endVertex < MAX_NUMBER_OF_NODES; endVertex++) {
            if (matrix[vertex.getId()][endVertex] != null) {
                matrix[vertex.getId()][endVertex] = null;
            }
        }
        // remove vertex
        vertexList[vertex.getId()] = null;
        return true;// return true if the removal is successful, false otherwise
    }

    /**
     * Returns a string of the depth first traversal of the graph.
     * We may need to perform depth first traversal for multiple rounds until all.
     * nodes.
     * are visited. Always pick the lowest ID vertex that has not been visited to.
     * start.
     * a round and all nodes reachable from that starting node should be traversed.
     * in.
     * this round before we move on to the next round.
     * @return a string representation of the depth first traversal, or an empty
     *         string if the graph is empty.
     */
    public String depthFirstTraversal() {
        // O(n^2) where n is the max number of vertices in the graph
        // Feel free to define private helpers.
        // Recursion can be helpful.
        // Make sure to use StringBuilder instead of String concatenation.
        StringBuilder string = new StringBuilder();
        LinkedList<Boolean> visited = new LinkedList<Boolean>();
        LinkedList<GraphNode> vertices = new LinkedList<GraphNode>(getVertices());
        for (int i = 0; i < MAX_NUMBER_OF_NODES; i++) {
            visited.add(false);
        }

        for (int i = 0; i < vertices.size(); i++) {
            if (visited.get(vertices.get(i).getId()).equals(false)) {// vertex was not visited yet
                string.append(dftHelper(vertices.get(i), visited));
            }
        }
        return string.toString().trim();
    }
    /**
     * helper method for depthFirstTraversal.
     * @param vertex takes unvisited vertex
     * @param visited keep track of visited/unvisited nodes
     * @return string contaning nodes
     */
    private String dftHelper(GraphNode vertex, LinkedList<Boolean> visited) {
        StringBuilder string = new StringBuilder();
        LinkedList<GraphNode> neighbor = new LinkedList<GraphNode>(getNeighbors(vertex));
        visited.set(vertex.getId(), true);
        string.append(vertex.getId());
        string.append(" ");
        // this loop is used to check neighbor
        for (GraphNode node : neighbor) {
            // starts with lowest ID neighbor
            if (visited.get(node.getId()).equals(false)) {// vertex's neighbor was not visited yet
                string.append(dftHelper(node, visited));
            }
        }
        return string.toString();
    }

    // ********************************************************************************
    // testing code goes here... edit this as much as you want!
    // ********************************************************************************

    /**
     * test cases.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        
        // create a set of nodes and edges to test with
        GraphNode[] nodes = {
            new GraphNode(0),
            new GraphNode(1),
            new GraphNode(2),
            new GraphNode(3),
            new GraphNode(4),
            new GraphNode(5),
            new GraphNode(6),
            new GraphNode(7),
            new GraphNode(8),
            new GraphNode(9)
        };

 
        GraphEdge[] edges = {
            new GraphEdge(0),
            new GraphEdge(1),
            new GraphEdge(2),
            new GraphEdge(3),
            new GraphEdge(4),
            new GraphEdge(5),
            new GraphEdge(6),
            new GraphEdge(7)
        };

        // construct a graph
        ThreeTenGraph graph = new ThreeTenGraph();
        for (GraphNode n : nodes) {
            graph.addVertex(n);
        }

        graph.addEdge(edges[0], nodes[0], nodes[1]);
        graph.addEdge(edges[1], nodes[1], nodes[2]);
        graph.addEdge(edges[2], nodes[3], nodes[6]);
        graph.addEdge(edges[3], nodes[6], nodes[7]);
        graph.addEdge(edges[4], nodes[8], nodes[9]);
        graph.addEdge(edges[5], nodes[9], nodes[0]);
        graph.addEdge(edges[6], nodes[2], nodes[7]);
        graph.addEdge(edges[7], nodes[1], nodes[8]);

        if (graph.getVertexCount() == 10 && graph.getEdgeCount() == 8) {
            System.out.println("Yay 1");
        }

        if (graph.inDegree(nodes[0]) == 1 && graph.outDegree(nodes[1]) == 2) {
            System.out.println("Yay 2");
        }

        // depth-first traversal
        if (graph.toString().trim().equals("0 1 2 7 8 9 3 6 4 5")) {
            System.out.println("Yay 3");
        }
    }

    // ********************************************************************************
    // YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
    // NOTE: you do need to fix JavaDoc issues if there is any in this section.
    // ********************************************************************************

    /**
     * Returns the number of edges incident to vertex.
     * Special cases of interest.
     * Incident self-loops are counted once.
     * If there is only one edge that connects this vertex to.
     * each of its neighbors (and vice versa), then the value returned.
     * will also be equal to the number of neighbors that this vertex has.
     * (that is, the output of getNeighborCount).
     * If the graph is directed, then the value returned will be.
     * the sum of this vertex's indegree (the number of edges whose.
     * destination is this vertex) and its outdegree (the number.
     * of edges whose source is this vertex), minus the number of.
     * incident self-loops (to avoid double-counting).
     * Equivalent to getIncidentEdges(vertex).size().
     * @param vertex the vertex whose degree is to be returned
     * @return the degree of this node
     * @see Hypergraph#getNeighborCount(Object)
     */
    public int degree(GraphNode vertex) {
        return inDegree(vertex) + outDegree(vertex);
    }

    /**
     * Returns true if v1 and v2 share an incident edge.
     * Equivalent to getNeighbors(v1).contains(v2).
     * @param v1 the first vertex to test
     * @param v2 the second vertex to test
     * @return true if v1 and v2 share an incident edge
     */
    public boolean isNeighbor(GraphNode v1, GraphNode v2) {
        return (findEdge(v1, v2) != null || findEdge(v2, v1) != null);
    }

    /**
     * Returns the endpoints of edge. 
     * @param edge the edge whose endpoints are to be returned
     * @return the endpoints (incident vertices) of edge
     */
    public Pair<GraphNode> getEndpoints(GraphEdge edge) {

        if (edge == null)
            return null;

        GraphNode v1 = getSource(edge);
        if (v1 == null)
            return null;

        GraphNode v2 = getDest(edge);
        if (v2 == null)
            return null;

        return new Pair<>(v1, v2);
    }

    /**
     * Returns the collection of edges in this graph which are connected to vertex.
     * @param vertex the vertex whose incident edges are to be returned
     * @return the collection of edges which are connected to vertex or null if vertex is not present
     */
    public Collection<GraphEdge> getIncidentEdges(GraphNode vertex) {
        LinkedList<GraphEdge> ret = new LinkedList<>();
        ret.addAll(getInEdges(vertex));
        ret.addAll(getOutEdges(vertex));
        return ret;
    }

    /**
     * Returns the collection of vertices in this graph which are connected to edge.
     * Note that for some graph types there are guarantees about the size of this collection.
     * (i.e., some graphs contain edges that have exactly two endpoints, which may or may not be distinct). 
     * Implementations for those graph types may provide alternate methods that provide more convenient access to the vertices.
     * @param edge the edge whose incident vertices are to be returned
     * @return the collection of vertices which are connected to edge or null if edge is not present
     */
    public Collection<GraphNode> getIncidentVertices(GraphEdge edge) {
        Pair<GraphNode> p = getEndpoints(edge);
        LinkedList<GraphNode> ret = new LinkedList<>();
        ret.add(p.getFirst());
        ret.add(p.getSecond());
        return ret;
    }

    /**
     * Returns true if this graph's edge collection contains edge.
     * Equivalent to getEdges().contains(edge).
     * @param edge the edge whose presence is being queried
     * @return true iff this graph contains an edge edge
     */
    public boolean containsEdge(GraphEdge edge) {
        return (getEndpoints(edge) != null);
    }

    /**
     * Returns the collection of edges in this graph which are of type edge_type.
     * @param edgeType the type of edges to be returned
     * @return the collection of edges which are of type edge_type or null if the graph does not accept edges of this type
     * @see EdgeType
     */
    public Collection<GraphEdge> getEdges(EdgeType edgeType) {
        if (edgeType == EdgeType.DIRECTED) {
            return getEdges();
        }
        return null;
    }

    /**
     * Returns the number of edges of type edge_type in this graph.
     * @param edgeType the type of edge for which the count is to be returned
     * @return the number of edges of type edge_type in this graph
     */
    public int getEdgeCount(EdgeType edgeType) {
        if (edgeType == EdgeType.DIRECTED) {
            return getEdgeCount();
        }
        return 0;
    }

    /**
     * Returns the number of predecessors that vertex has in this graph.
     * Equivalent to vertex.getPredecessors().size().
     * @param vertex the vertex whose predecessor count is to be returned
     * @return the number of predecessors that vertex has in this graph
     */
    public int getPredecessorCount(GraphNode vertex) {
        return inDegree(vertex);
    }

    /**
     * Returns the number of successors that vertex has in this graph.
     * Equivalent to vertex.getSuccessors().size().
     * @param vertex the vertex whose successor count is to be returned
     * @return the number of successors that vertex has in this graph
     */
    public int getSuccessorCount(GraphNode vertex) {
        return outDegree(vertex);
    }

    /**
     * Returns the vertex at the other end of edge from vertex.
     * (That is, returns the vertex incident to edge which is not vertex.)
     * @param vertex the vertex to be queried
     * @param edge   the edge to be queried
     * @return the vertex at the other end of edge from vertex
     */
    public GraphNode getOpposite(GraphNode vertex, GraphEdge edge) {
        if (getSource(edge).equals(vertex)) {
            return getDest(edge);
        } else if (getDest(edge).equals(vertex)) {
            return getSource(edge);
        } else
            return null;
    }

    /**
     * Returns all edges that connects v1 to v2.
     * If this edge is not uniquely defined 
     * (that is, if the graph contains more than one edge connecting v1 to v2).
     * any of these edges may be returned. 
     * findEdgeSet(v1, v2) may be used to return all such edges.
     * Returns null if v1 is not connected to v2.
     * Returns an empty collection if either v1 or v2 are not present in this graph.
     * Note: for purposes of this method. 
     *  v1 is only considered to be connected to
     *  v2 via a given <i>directed</i> edge d if
     *  v1 == d.getSource() && v2 == d.getDest() evaluates to true.
     *  (v1 and v2 are connected by an undirected edge u if u is incident to both v1 and v2).
     * @param v1 vertex1
     * @param v2 vertex2
     * @return a collection containing all edges that connect v1 to v2 or null if either vertex is not present
     * @see Hypergraph#findEdge(Object, Object)
     */
    public Collection<GraphEdge> findEdgeSet(GraphNode v1, GraphNode v2) {
        GraphEdge edge = findEdge(v1, v2);
        if (edge == null) {
            return null;
        }

        LinkedList<GraphEdge> ret = new LinkedList<>();
        ret.add(edge);
        return ret;

    }

    /**
     * Returns true if vertex is the source of edge.
     * Equivalent to getSource(edge).equals(vertex).
     * @param vertex the vertex to be queried
     * @param edge   the edge to be queried
     * @return true iff vertex is the source of edge
     */
    public boolean isSource(GraphNode vertex, GraphEdge edge) {
        return getSource(edge).equals(vertex);
    }

    /**
     * Returns true if vertex is the destination of edge.
     * Equivalent to getDest(edge).equals(vertex).
     * @param vertex the vertex to be queried
     * @param edge   the edge to be queried
     * @return true iff vertex is the destination of edge
     */
    public boolean isDest(GraphNode vertex, GraphEdge edge) {
        return getDest(edge).equals(vertex);
    }

    /**
     * Adds edge e to this graph such that it connects vertex v1 to v2.
     * If this graph does not contain v1, v2, or both.
     * Implementations may choose to either silently add the vertices to the graph.
     * Or throw an IllegalArgumentException.
     * If edgeType is not legal for this graph, this method will
     * throw IllegalArgumentException.
     * See Hypergraph.addEdge() for a listing of possible reasons
     * for failure.
     * @param e        the edge to be added
     * @param v1       the first vertex to be connected
     * @param v2       the second vertex to be connected
     * @param edgeType the type to be assigned to the edge
     * @return true if the add is successful, false otherwise
     * @see Hypergraph#addEdge(Object, Collection)
     * @see #addEdge(Object, Object, Object)
     */
    public boolean addEdge(GraphEdge e, GraphNode v1, GraphNode v2, EdgeType edgeType) {
        // NOTE: Only directed edges allowed

        if (edgeType == EdgeType.UNDIRECTED) {
            throw new IllegalArgumentException();
        }

        return addEdge(e, v1, v2);
    }

    /**
     * Adds edge to this graph.
     * Fails under the following circumstances.
     *  edge is already an element of the graph.
     *  either edge or vertices is null.
     *  vertices has the wrong number of vertices for the graph type.
     *  vertices are already connected by another edge in this graph.
     * This graph does not accept parallel edges.
     * @param edge edge
     * @param vertices list of vertices
     * @return true if the add is successful, and false otherwise
     * @throws IllegalArgumentException if edge or vertices is null,
     *                                  or if a different vertex set in this graph
     *                                  is already connected by edge.
     *                                  or if vertices are not a legal vertex set
     *                                  for edge.
     */
    @SuppressWarnings("unchecked")
    public boolean addEdge(GraphEdge edge, Collection<? extends GraphNode> vertices) {
        if (edge == null || vertices == null || vertices.size() != 2) {
            return false;
        }

        GraphNode[] vs = (GraphNode[]) vertices.toArray();
        return addEdge(edge, vs[0], vs[1]);
    }

    /**
     * Adds edge to this graph with type edge_type.
     * Fails under the following circumstances.
     *  edge is already an element of the graph.
     *  either edge or vertices is null.
     *  vertices has the wrong number of vertices for the graph type.
     *  vertices are already connected by another edge in this graph.
     * This graph does not accept parallel edges.
     * edge_type is not legal for this graph.
     * @param edge edge
     * @param vertices list of vertices
     * @param edgeType edge type
     * @return true if the add is successful, and false otherwise
     * @throws IllegalArgumentException if edge or vertices is null,
     *                                  or if a different vertex set in this graph
     *                                  is already connected by edge,
     *                                  or if vertices are not a legal vertex set
     *                                  for edge
     */
    @SuppressWarnings("unchecked")
    public boolean addEdge(GraphEdge edge, Collection<? extends GraphNode> vertices, EdgeType edgeType) {
        if (edge == null || vertices == null || vertices.size() != 2) {
            return false;
        }

        GraphNode[] vs = (GraphNode[]) vertices.toArray();
        return addEdge(edge, vs[0], vs[1], edgeType);
    }

    // ********************************************************************************
    // DO NOT EDIT ANYTHING BELOW THIS LINE EXCEPT FOR FIXING JAVADOC
    // ********************************************************************************

    /**
     * Returns a {@code Factory} that creates an instance of this graph type.
     * @param <V> any type
     * @param <E> any type
     * @return a new Factory object
     */

    public static <V, E> Factory<Graph<GraphNode, GraphEdge>> getFactory() {
        return new Factory<Graph<GraphNode, GraphEdge>>() {
            @SuppressWarnings("unchecked")
            public Graph<GraphNode, GraphEdge> create() {
                return (Graph<GraphNode, GraphEdge>) new ThreeTenGraph();
            }
        };
    }

    /**
     * Returns the edge type of edge in this graph.
     * @param edge edge
     * @return the EdgeType of edge, or null if edge has no defined type
     */
    public EdgeType getEdgeType(GraphEdge edge) {
        return EdgeType.DIRECTED;
    }

    /**
     * Returns the default edge type for this graph.
     * @return the default edge type for this graph
     */
    public EdgeType getDefaultEdgeType() {
        return EdgeType.DIRECTED;
    }

    /**
     * Returns the number of vertices that are incident to edge.
     * For hyperedges, this can be any nonnegative integer; for edges this.
     * must be 2 (or 1 if self-loops are permitted).
     * Equivalent to getIncidentVertices(edge).size().
     * @param edge the edge whose incident vertex count is to be returned
     * @return the number of vertices that are incident to edge.
     */
    public int getIncidentCount(GraphEdge edge) {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return depthFirstTraversal();
    }

}
