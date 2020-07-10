package Graph.TopologicalOrder.DAG;

/*
Topological sorting/order is a linear ordering of its vertices such that for every directed edge uv, u comes before v in the ordering.
not unique
Not every graph can have a topological ordering,  graph has cycle

Only DAG has topolical order

Tarjan's strongly connected component algorithm to find cycles.
rooted tree ->pick leaves

/////// DFS ///////////
picked an unvisited node arbitrary, beginning with the selected node, do DFS exploring only unvisited nodes.
on the recursive callback of the DFS, add the current node to the topological ordering in reverse order.
*/

public class TopologicalSortingDAG {
}
