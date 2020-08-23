package Graph;

/*
https://www.techiedelight.com/cost-of-shortest-path-in-dag-using-one-pass-of-bellman-ford/
Given a DAG and a source vertex, find the cost of shortest path from source vertex to the other
 */
public class FindShortestDistance {
    /*
    We can use topological sort to solve this problem. When we consider a vertex u in topological order, it is guaranteed that
    we have considered every incoming edge to it. Now for each vertex v of the DAG in the topological order, we relax cost of itsoutging edges
     in order words since we have already found shorest path to vertex we can use that info to update shortest path of all its adjacent vertices.
     */
}
